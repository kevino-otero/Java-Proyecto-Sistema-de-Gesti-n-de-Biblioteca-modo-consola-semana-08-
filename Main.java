package ProgramacionII.RegistroII.Semana8;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    // Códigos ANSI para dar color a la consola
    public static final String RESET = "\u001B[0m";
    public static final String AZUL = "\u001B[34m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String ROJO = "\u001B[31m";
    public static final String MORADO = "\u001B[35m";
    public static final String CIAN = "\u001B[36m";

    // Versión de la app
    public static final String VERSION = "v1.0.1";

    // Instancia de la clase Biblioteca y Scanner para la entrada del usuario
    private static final Biblioteca b = new Biblioteca();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Mensaje de bienvenida con efecto de escritura
        escribirLento(AZUL + "=== Sistema de Gestión de Biblioteca " + VERSION + " ===" + RESET);
        escribirLento(CIAN + "Iniciando la aplicación..." + RESET);

        // Intenta cargar los datos al inicio del programa
        try {
            b.cargar();
        } catch (IOException e) {
            escribirLento(ROJO + "Error: No se pudieron cargar los datos iniciales." + RESET);
        }

        // Bucle principal del menú
        while (true) {
            mostrarMenu();
            System.out.print(AMARILLO + "Elige una opción: " + RESET);

            try {
                String entrada = sc.nextLine().trim();
                if (entrada.equalsIgnoreCase("q")) {
                    escribirLento(AZUL + "Saliendo del programa. ¡Hasta pronto!" + RESET);
                    return;
                }

                int op = Integer.parseInt(entrada);

                // Estructura de control para ejecutar la opción elegida
                switch (op) {
                    case 1 -> registrarLibro();
                    case 2 -> registrarUsuario();
                    case 3 -> prestarLibro();
                    case 4 -> devolverLibro();
                    case 5 -> listarLibros();
                    case 6 -> listarPorGenero();
                    case 7 -> listarPorAutor();
                    case 8 -> listarPorDisponibilidad();
                    case 9 -> b.guardar();
                    case 10 -> b.cargar();
                    case 0 -> {
                        escribirLento(AZUL + "Saliendo del programa. ¡Hasta pronto!" + RESET);
                        return; // Sale del método main y termina el programa
                    }
                    default -> escribirLento(ROJO + "Opción inválida. Inténtalo de nuevo." + RESET);
                }
            } catch (IOException e) {
                // Captura errores relacionados con la lectura/escritura de archivos
                escribirLento(ROJO + "Error de archivo: " + e.getMessage() + RESET);
            } catch (NumberFormatException e) {
                // Captura errores si el usuario no introduce un número
                escribirLento(ROJO + "Entrada inválida. Por favor, ingresa un número." + RESET);
            }
        }
    }

    // Muestra el menú de opciones
    private static void mostrarMenu() {
        escribirLento(AMARILLO + "\n----------------------------------------" + RESET);
        escribirLento(AZUL + "             Menú Principal            " + RESET);
        escribirLento(AMARILLO + "----------------------------------------" + RESET);
        escribirLento(VERDE + "1. Registrar libro");
        escribirLento("2. Registrar usuario");
        escribirLento("3. Prestar libro");
        escribirLento("4. Devolver libro");
        escribirLento("5. Listar libros");
        escribirLento("6. Listar por género");
        escribirLento("7. Listar por autor");
        escribirLento("8. Listar por disponibilidad");
        escribirLento("9. Guardar datos");
        escribirLento("10. Cargar datos");
        escribirLento("0. Salir (o Q)" + RESET);
        escribirLento(AMARILLO + "----------------------------------------" + RESET);
    }

    // Lógica para registrar un libro
    private static void registrarLibro() throws IOException {
        escribirLento(MORADO + "\n--- Nuevo Libro ---" + RESET);
        System.out.print(AMARILLO + "ID: " + RESET); int id = Integer.parseInt(sc.nextLine());
        System.out.print(AMARILLO + "Título: " + RESET); String t = sc.nextLine();
        System.out.print(AMARILLO + "Autor: " + RESET); String a = sc.nextLine();
        System.out.print(AMARILLO + "Año: " + RESET); int an = Integer.parseInt(sc.nextLine());
        System.out.print(AMARILLO + "Género: " + RESET); String g = sc.nextLine();
        b.registrarLibro(new Libro(id, t, a, an, g));
        escribirLento(VERDE + "Libro registrado con éxito." + RESET);
    }

    // Lógica para registrar un usuario
    private static void registrarUsuario() throws IOException {
        escribirLento(MORADO + "\n--- Nuevo Usuario ---" + RESET);
        System.out.print(AMARILLO + "ID usuario: " + RESET); int id = Integer.parseInt(sc.nextLine());
        System.out.print(AMARILLO + "Nombre: " + RESET); String n = sc.nextLine();
        System.out.print(AMARILLO + "Identificación: " + RESET); String idf = sc.nextLine();
        b.registrarUsuario(new Usuario(id, n, idf));
        escribirLento(VERDE + "Usuario registrado con éxito." + RESET);
    }

    // Lógica para prestar un libro
    private static void prestarLibro() throws IOException {
        escribirLento(MORADO + "\n--- Préstamo ---" + RESET);
        System.out.print(AMARILLO + "ID del libro a prestar: " + RESET);
        b.prestarLibro(Integer.parseInt(sc.nextLine()));
    }

    // Lógica para devolver un libro
    private static void devolverLibro() throws IOException {
        escribirLento(MORADO + "\n--- Devolución ---" + RESET);
        System.out.print(AMARILLO + "ID del libro a devolver: " + RESET);
        b.devolverLibro(Integer.parseInt(sc.nextLine()));
    }

    // Lógica para listar todos los libros
    private static void listarLibros() {
        escribirLento(MORADO + "\n--- Listado de Libros ---" + RESET);
        b.listarLibros();
    }

    // Lógica para listar libros por género
    private static void listarPorGenero() {
        escribirLento(MORADO + "\n--- Búsqueda por Género ---" + RESET);
        System.out.print(AMARILLO + "Ingresa el género: " + RESET);
        b.listarPorGenero(sc.nextLine());
    }

    // Lógica para listar libros por autor
    private static void listarPorAutor() {
        escribirLento(MORADO + "\n--- Búsqueda por Autor ---" + RESET);
        System.out.print(AMARILLO + "Ingresa el autor: " + RESET);
        b.listarPorAutor(sc.nextLine());
    }

    // Lógica para listar libros por disponibilidad
    private static void listarPorDisponibilidad() {
        escribirLento(MORADO + "\n--- Búsqueda por Disponibilidad ---" + RESET);
        System.out.print(AMARILLO + "¿Disponible? (true/false): " + RESET);
        try {
            b.listarPorDisponibilidad(Boolean.parseBoolean(sc.nextLine()));
        } catch (Exception e) {
            escribirLento(ROJO + "Entrada inválida. Por favor, escribe 'true' o 'false'." + RESET);
        }
    }

    // Método que simula el efecto de escribir lentamente
    private static void escribirLento(String texto) {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(15); // Pausa de 15 milisegundos por cada caracter
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
}
