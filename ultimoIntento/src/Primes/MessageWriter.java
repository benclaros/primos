package Primes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MessageWriter {
    public static void writePrimesToFile(List<Integer> primes, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Integer prime : primes) {
                writer.write("Código Primo: " + prime + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}