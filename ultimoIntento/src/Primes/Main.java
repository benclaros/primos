package Primes;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Set<Integer> numerosIngresados = new TreeSet<>();
    private static final PrimesList listaFinal = new PrimesList();

    public static void main(String[] args) {
        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Ingresar números manualmente (1-100)");
            System.out.println("2. Mostrar lista de primos");
            System.out.println("3. Exportar resultados a archivo");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            String input = scanner.next();
            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(" Entrada inválida. Debes ingresar un número del 1 al 5.");
                continue;
            }

            switch (opcion) {
                case 1:
                    ingresarNumeros();
                    break;
                case 2:
                    mostrarPrimos();
                    break;
                case 3:
                    exportarResultados();
                    break;
                case 4:
                    System.out.println(" Saliendo del programa...");
                    break;
                default:
                    System.out.println(" Opción inválida.");
            }
        } while (opcion != 4);
    }

private static void ingresarNumeros() {
    System.out.println("Ingresa números entre 1 y 100 (0 para terminar):");

    while (true) {
        System.out.print("Número: ");
        String input = scanner.next();

        try {
            int num = Integer.parseInt(input);
            if (num == 0) break;

            if (num < 1 || num > 100) {
                System.out.println("❌ Número fuera del rango permitido (1-100). Intenta de nuevo.");
                continue;
            }

            if (!numerosIngresados.contains(num)) {
                numerosIngresados.add(num);

                if (listaFinal.isPrime(num)) {
                    if (listaFinal.addPrime(num)) {
                        System.out.println(num + " es primo y fue agregado a la lista.");
                        CSVUpdater.appendIfNotExists(num);

                        // 🆕 Encriptar mensaje asociado a ese número primo
                        scanner.nextLine(); // limpiar buffer
                        System.out.print("Ingresa un mensaje para encriptar con " + num + ": ");
                        String mensaje = scanner.nextLine();

                        String encriptado = MessageEncryptor.encryptMessage(mensaje, num);
                        MessageEncryptor.saveToFile(mensaje, encriptado, num);

                        System.out.println("✅ Mensaje encriptado y guardado exitosamente.");
                    } else {
                        System.out.println(num + " es primo, pero ya estaba en la lista.");
                    }
                } else {
                    System.out.println(num + " no es primo.");
                }

            } else {
                System.out.println("Número ya ingresado.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Por favor ingresa un número entero entre 1 y 100.");
        }
    }
}


    private static void mostrarPrimos() {
        List<Integer> primos = listaFinal.getSortedPrimes();
        if (primos.isEmpty()) {
            System.out.println("No hay primos registrados.");
        } else {
            System.out.println("Lista de primos:");
            for (Integer p : primos) {
                System.out.println(p);
            }
        }
    }

    private static void exportarResultados() {
        MessageWriter.writePrimesToFile(listaFinal.getSortedPrimes(), "mensajes.txt");
        System.out.println(" Se guardaron los primos en mensajes.txt");
    }
}
