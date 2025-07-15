package Primes;

import java.util.Queue;

public class PrimesThread implements Runnable {
    private final Queue<Integer> numberQueue;
    private final PrimesList primesList;

    public PrimesThread(Queue<Integer> numberQueue, PrimesList primesList) {
        this.numberQueue = numberQueue;
        this.primesList = primesList;
    }

    @Override
    public void run() {
        while (true) {
            Integer number;
            synchronized (numberQueue) {
                number = numberQueue.poll();
            }
            if (number == null) break;

            if (primesList.addPrime(number)) {
                System.out.println("Hilo " + Thread.currentThread().getName() + ": " + number + " es primo.");
            }
        }
    }
}