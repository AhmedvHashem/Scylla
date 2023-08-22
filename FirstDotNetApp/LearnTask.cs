namespace FirstDotNetApp;

public static class LearnTask
{
    public static void Maasdin(string[] args)
    {
        Console.WriteLine("Hello World! C# Program");
        Console.WriteLine("Environment.CurrentManagedThreadId: " + Environment.CurrentManagedThreadId);

        var t = Task.Run(() =>
        {
            Console.WriteLine("Environment.CurrentManagedThreadId: " + Environment.CurrentManagedThreadId);
            Console.WriteLine("Task");

            var t2 = Task.Run(() =>
            {
                Console.WriteLine("Environment.CurrentManagedThreadId: " + Environment.CurrentManagedThreadId);
                Console.WriteLine("Task2");
            });
            t2.Wait();
        });

        Console.WriteLine("Main before wait");
        t.Wait();
        Console.WriteLine("Main after wait");
    }

    public static async Task Main(string[] args)
    {
        // part 1
        Console.WriteLine("Environment.CurrentManagedThreadId: " + Environment.CurrentManagedThreadId);
        var client = new HttpClient();
        Console.WriteLine("Environment.CurrentManagedThreadId: " + Environment.CurrentManagedThreadId);
        var task = client.GetStringAsync("http://google.com");
        Console.WriteLine("Environment.CurrentManagedThreadId: " + Environment.CurrentManagedThreadId);

        var a = 0;
        for (int i = 0; i < 1_000_000; i++)
        {
            a += i;
        }

        Console.WriteLine("Environment.CurrentManagedThreadId: " + Environment.CurrentManagedThreadId);
        var page = await task;
        // part 2
        Console.WriteLine("Environment.CurrentManagedThreadId: " + Environment.CurrentManagedThreadId);
    }
}