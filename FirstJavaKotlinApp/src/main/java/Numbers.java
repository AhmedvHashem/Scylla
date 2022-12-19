/**
 * Created by AhmedNTS on 6/14/2017.
 */
public class Numbers {

    public static void main(String[] args) {
        System.out.println("Modulo : " + (4 % 20));

        fibonacci(0, 1, 0, 10);
    }

    static void fibonacci(int f0, int f1, int fib, int n) {
        if (n > 0) {
            fib = f0 + f1;
            f0 = f1;
            f1 = fib;

            System.out.print(" " + fib);

            fibonacci(f0, f1, fib, n - 1);
        }
    }

    /*
     * Prime number is not divisible by any number other than 1 and itself
     * 
     * @return true if number is prime
     */
    public static boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false; // number is divisible so its not prime
            }
        }
        return true; // number is prime now
    }

    /*
     * reverse a number in Java using iteration
     * 
     * @return reverse of number
     */
    public static int reverse(int number) {
        int reverse = 0;
        int remainder = 0;
        do {
            remainder = number % 10;
            reverse = reverse * 10 + remainder;
            number = number / 10;

        } while (number > 0);

        return reverse;
    }
}
