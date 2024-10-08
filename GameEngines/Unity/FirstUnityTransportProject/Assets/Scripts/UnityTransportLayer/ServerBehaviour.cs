using System;
using UnityEngine;
using Unity.Networking.Transport;
using Unity.Collections;
using UnityEngine.Assertions;

namespace UnityTransportLayer
{
    public class ServerBehaviour : MonoBehaviour
    {
        private NetworkDriver m_Driver;

        //private NetworkPipeline m_UnreliablePipeline;
        private NetworkPipeline m_SequencedPipeline;
        private NativeList<NetworkConnection> m_Connections;

        void Start()
        {
            m_Driver = NetworkDriver.Create(new UDPNetworkInterface());
            //m_UnreliablePipeline = NetworkPipeline.Null;
            m_SequencedPipeline = m_Driver.CreatePipeline(typeof(UnreliableSequencedPipelineStage));

            var endpoint = NetworkEndpoint.AnyIpv4;
            endpoint.Port = 9000;
            if (m_Driver.Bind(endpoint) != 0)
                Debug.Log("Failed to bind to port 9000");
            else
                m_Driver.Listen();

            m_Connections = new NativeList<NetworkConnection>(16, Allocator.Persistent);
        }

        public void OnDestroy()
        {
            m_Driver.Dispose();
            m_Connections.Dispose();
        }

        void Update()
        {
            m_Driver.ScheduleUpdate().Complete();

            // CleanUpConnections
            for (int i = 0; i < m_Connections.Length; i++)
            {
                if (!m_Connections[i].IsCreated)
                {
                    m_Connections.RemoveAtSwapBack(i);
                    --i;
                }
            }

            Debug.Log("Accepting new connection");
            // AcceptNewConnections
            NetworkConnection c;
            while ((c = m_Driver.Accept()) != default(NetworkConnection))
            {
                Debug.Log("While Accepting new connection");

                m_Connections.Add(c);
                Debug.Log("Accepted a connection");
            }
            Debug.Log($"End While Accepting new connection");

            DataStreamReader stream;
            for (int i = 0; i < m_Connections.Length; i++)
            {
                if (!m_Connections[i].IsCreated)
                    Assert.IsTrue(true);

                NetworkEvent.Type cmd;
                while ((cmd = m_Driver.PopEventForConnection(m_Connections[i], out stream)) != NetworkEvent.Type.Empty)
                {
                    if (cmd == NetworkEvent.Type.Data)
                    {
                        uint number = stream.ReadUInt();

                        Debug.Log($"Got {number} from the Client adding + 2 to it.");
                        number += 2;

                        if (m_Driver.BeginSend(m_SequencedPipeline, m_Connections[i], out var writer) == 0)
                        {
                            writer.WriteUInt(number);
                            m_Driver.EndSend(writer);
                        }
                    }
                    else if (cmd == NetworkEvent.Type.Disconnect)
                    {
                        Debug.Log("Client disconnected from server");
                        m_Connections[i] = default(NetworkConnection);
                    }
                }
            }
        }
    }
}