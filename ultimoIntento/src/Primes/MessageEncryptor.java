package Primes;

import java.io.FileWriter;
import java.io.IOException;

public class MessageEncryptor {

    // Cifra el mensaje con cifrado César usando el número primo como clave
    public static String encryptMessage(String message, int primeCode) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char shifted = (char) ((c - base + primeCode) % 26 + base);
                encrypted.append(shifted);
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    // Guarda el mensaje encriptado en un archivo
    public static void saveToFile(String original, String encrypted, int primeCode) {
        try (FileWriter fw = new FileWriter("mensajes.txt", true)) {
            fw.write("Mensaje original: " + original + "\n");
            fw.write("Mensaje encriptado: " + encrypted + "\n");
            fw.write("Código Primo usado: " + primeCode + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
