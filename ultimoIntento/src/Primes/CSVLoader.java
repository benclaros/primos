package Primes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class CSVLoader {
    public static Queue<Integer> loadNumbersFromCSV(String filename) {
        Queue<Integer> numbers = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String part : line.split(",")) {
                    try {
                        numbers.add(Integer.parseInt(part.trim()));
                    } catch (NumberFormatException ignored) {}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }
}