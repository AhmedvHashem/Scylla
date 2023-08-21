namespace FirstDotNetApp;

public static class LearnTask
{
    public static void Main(string[] args)
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
}