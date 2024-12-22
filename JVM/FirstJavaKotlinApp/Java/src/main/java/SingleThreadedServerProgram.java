import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SingleThreadedServerProgram {
    public static void main(String[] args) {
        // String dateTime = VDateTime.JODA_ConvertTimeZones("2016-1-1T00:00:00",
        // DateTimeZone.getDefault(), DateTimeZone.UTC);
        // System.out.println(dateTime);

        // String dateTime2 = VDateTime.JODA_ConvertTimeZones("2016-1-1T00:00:00",
        // DateTimeZone.UTC, DateTimeZone.getDefault());
        // System.out.println(dateTime2);

        SingleThreadedServer server = new SingleThreadedServer(9000);
        new Thread(server).start();

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
    }
}

class SingleThreadedServer implements Runnable {
    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Thread runningThread = null;

    public SingleThreadedServer(int port) {
        this.serverPort = port;
    }

    @Override
    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();

        while (!isStopped()) {
            Socket clientSocket = null;

            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }

            new Thread(new WorkerRunnable(clientSocket, "Multithreaded Server")).start();

            // try
            // {
            // processClientRequest(clientSocket);
            // }
            // catch (IOException e)
            // {
            // //log exception and go on to next request.
            // }
        }

        System.out.println("Server Stopped.");
    }

    private void processClientRequest(Socket clientSocket) throws IOException {
        InputStream input = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();
        long time = System.currentTimeMillis();

        output.write(
                ("HTTP/1.1 200 OK\n\n<html><body>" + "Singlethreaded Server: " + time + "</body></html>").getBytes());
        output.close();
        input.close();
        System.out.println("Request processed: " + time);
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }
}

class WorkerRunnable implements Runnable {
    protected Socket clientSocket = null;
    protected String serverText = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    @Override
    public void run() {
        try (InputStream input = clientSocket.getInputStream(); OutputStream output = clientSocket.getOutputStream()) {
            long time = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " + this.serverText + " - " + time + "").getBytes());
        } catch (IOException e) {
            // report exception somewhere.
            e.printStackTrace();
        }
        // System.out.println("Request processed: " + time);
    }
}
