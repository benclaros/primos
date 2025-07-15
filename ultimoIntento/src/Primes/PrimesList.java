package Primes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;

public class PrimesList {
    private final ArrayList<Integer> primes = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();

    // Verifica si un número es primo
    public boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    // Agrega un número primo si no está repetido
    public boolean addPrime(int number) {
        lock.lock();
        try {
            if (!isPrime(number)) return false;
            if (primes.contains(number)) return false;
            primes.add(number);
            return true;
        } finally {
            lock.unlock();
        }
    }

    // Devuelve la cantidad total de primos
    public int getPrimesCount() {
        lock.lock();
        try {
            return primes.size();
        } finally {
            lock.unlock();
        }
    }

    // Devuelve una copia ordenada de la lista de primos
    public ArrayList<Integer> getSortedPrimes() {
        lock.lock();
        try {
            ArrayList<Integer> copy = new ArrayList<>(primes);
            Collections.sort(copy);
            return copy;
        } finally {
            lock.unlock();
        }
    }
}
