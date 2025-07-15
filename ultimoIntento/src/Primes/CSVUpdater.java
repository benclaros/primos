package Primes;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CSVUpdater {
    private static final String FILE_NAME = "primos.csv";

    // Cargar primos existentes del archivo
    public static Set<Integer> loadExistingPrimes() {
        Set<Integer> existing = new HashSet<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return existing;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String s : line.split(",")) {
                    try {
                        existing.add(Integer.parseInt(s.trim()));
                    } catch (NumberFormatException ignored) {}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return existing;
    }

    // Agrega un número nuevo si no está repetido
    public static void appendIfNotExists(int prime) {
        Set<Integer> existing = loadExistingPrimes();
        if (!existing.contains(prime)) {
            try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
                if (new File(FILE_NAME).length() > 0) {
                    fw.write(",");
                }
                fw.write(String.valueOf(prime));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
