package Primes;

import java.io.*;
import java.util.*;

public class FileManager {

    public static List<Integer> readPrimesFromCSV(String path) {
        List<Integer> primes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    primes.add(Integer.valueOf(line.trim()));
                } catch (NumberFormatException ignored) {}
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }
        return primes;
    }

    public static void writeEncryptedMessage(String path, String message, List<Integer> primes) {
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write("Mensaje: " + message + "\n");
            writer.write("Primos usados: " + primes + "\n\n");
        } catch (IOException e) {
            System.out.println("Error escribiendo el archivo: " + e.getMessage());
        }
    }
}
