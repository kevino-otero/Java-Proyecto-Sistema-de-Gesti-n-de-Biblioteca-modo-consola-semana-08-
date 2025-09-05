package ProgramacionII.RegistroII.Semana8;


import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca b = new Biblioteca();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Biblioteca (consola) ===");
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Listar libros");
            System.out.println("6. Listar por género");
            System.out.println("7. Listar por autor");
            System.out.println("8. Listar por disponibilidad");
            System.out.println("9. Guardar datos");
            System.out.println("10. Cargar datos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            int op = Integer.parseInt(sc.nextLine().trim());
            try {
                switch (op) {
                    case 1 -> {
                        System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Título: "); String t = sc.nextLine();
                        System.out.print("Autor: "); String a = sc.nextLine();
                        System.out.print("Año: "); int an = Integer.parseInt(sc.nextLine());
                        System.out.print("Género: "); String g = sc.nextLine();
                        b.registrarLibro(new Libro(id, t, a, an, g));
                        System.out.println("Libro registrado.");
                    }
                    case 2 -> {
                        System.out.print("ID usuario: "); int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nombre: "); String n = sc.nextLine();
                        System.out.print("Identificación: "); String idf = sc.nextLine();
                        b.registrarUsuario(new Usuario(id, n, idf));
                        System.out.println("Usuario registrado.");
                    }
                    case 3 -> { System.out.print("ID libro: "); b.prestarLibro(Integer.parseInt(sc.nextLine())); }
                    case 4 -> { System.out.print("ID libro: "); b.devolverLibro(Integer.parseInt(sc.nextLine())); }
                    case 5 -> b.listarLibros();
                    case 6 -> { System.out.print("Género: "); b.listarPorGenero(new String(sc.nextLine())); }
                    case 7 -> { System.out.print("Autor: "); b.listarPorAutor(new String(sc.nextLine())); }
                    case 8 -> {
                        System.out.print("¿Disponible? (true/false): ");
                        b.listarPorDisponibilidad(Boolean.parseBoolean(sc.nextLine()));
                    }
                    case 9 -> b.guardar();
                    case 10 -> b.cargar();
                    case 0 -> { System.out.println("Adiós"); return; }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (IOException e) {
                System.out.println("Error de archivo: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
    }
}


