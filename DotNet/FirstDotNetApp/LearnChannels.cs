using System.Threading.Channels;

namespace FirstDotNetApp;

public static class LearnChannels
{
    public static void Main(string[] args)
    {
        Console.WriteLine("Hello World! C# Program");
        Console.WriteLine("Environment.CurrentManagedThreadId: " + Environment.CurrentManagedThreadId);

        var channel = Channel.CreateUnbounded<int>();

        var task = Task.Run(async () =>
        {
            Console.WriteLine("Environment.CurrentManagedThreadId: " + Environment.CurrentManagedThreadId);

            var i = 0;
            while (true)
            {
                Console.WriteLine("Task before TryWrite");
                await Task.Delay(2000);
                channel.Writer.TryWrite(++i);
                Console.WriteLine("Task after TryWrite");
            }
        });

        var i = 0;
        while (true)
        {
            Thread.Sleep(3000);
            Console.WriteLine("Main before TryRead");
            if (channel.Reader.TryRead(out i))
                Console.WriteLine(i);
            Console.WriteLine("Main after TryRead");
        }
    }
}

// public sealed class Channel<T>
// {
//     private readonly ConcurrentQueue<T> _queue = new ConcurrentQueue<T>();
//     private readonly SemaphoreSlim _semaphore = new SemaphoreSlim(0);

//     public void Write(T value)
//     {
//         _queue.Enqueue(value); // store the data
//         _semaphore.Release(); // notify any consumers that more data is available
//     }

//     public async ValueTask<T> ReadAsync(CancellationToken cancellationToken = default)
//     {
//         await _semaphore.WaitAsync(cancellationToken).ConfigureAwait(false); // wait
//         bool gotOne = _queue.TryDequeue(out T item); // retrieve the data
//         Debug.Assert(gotOne);
//         return item;
//     }
// }