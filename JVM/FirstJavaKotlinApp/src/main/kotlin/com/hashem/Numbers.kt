package com.hashem

fun main(args: Array<String>) {
    println("Modulo : " + 4 % 20)

    fibonacci(0, 1, 0, 10)
}

fun fibonacci(f0: Int, f1: Int, fib: Int, n: Int) {
    var f0 = f0
    var f1 = f1
    var fib = fib
    if (n > 0) {
        fib = f0 + f1
        f0 = f1
        f1 = fib
        print(" $fib")
        fibonacci(f0, f1, fib, n - 1)
    }
}

/*
 * Prime number is not divisible by any number other than 1 and itself
 *
 * @return true if number is prime
 */
fun isPrime(number: Int): Boolean {
    for (i in 2 until number) {
        if (number % i == 0) {
            return false // number is divisible so its not prime
        }
    }
    return true // number is prime now
}

/*
 * reverse a number in Java using iteration
 *
 * @return reverse of number
 */
fun reverse(number: Int): Int {
    var number = number
    var reverse = 0
    var remainder = 0
    do {
        remainder = number % 10
        reverse = reverse * 10 + remainder
        number /= 10
    } while (number > 0)
    return reverse
}
