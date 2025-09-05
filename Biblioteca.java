package ProgramacionII.RegistroII.Semana8;

import java.io.*;
import java.util.*;

public class Biblioteca {
    private List<Libro> libros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    // ====== Libros ======
    public void registrarLibro(Libro l) { libros.add(l); }
    public Libro buscarLibroPorId(int id) {
        return libros.stream().filter(l -> l.getId()==id).findFirst().orElse(null);
    }
    public void prestarLibro(int idLibro) {
        Libro l = buscarLibroPorId(idLibro);
        if (l == null) { System.out.println("No existe el libro."); return; }
        if (!l.isDisponible()) { System.out.println("Ya está prestado."); return; }
        l.setDisponible(false);
        System.out.println("Préstamo registrado.");
    }
    public void devolverLibro(int idLibro) {
        Libro l = buscarLibroPorId(idLibro);
        if (l == null) { System.out.println("No existe el libro."); return; }
        l.setDisponible(true);
        System.out.println("Devolución registrada.");
    }
    public void listarLibros() {
        if (libros.isEmpty()) { System.out.println("No hay libros."); return; }
        libros.forEach(System.out::println);
    }
    public void listarPorGenero(String genero) {
        libros.stream().filter(l -> l.getGenero().equalsIgnoreCase(genero))
                .forEach(System.out::println);
    }
    public void listarPorAutor(String autor) {
        libros.stream().filter(l -> l.getAutor().equalsIgnoreCase(autor))
                .forEach(System.out::println);
    }
    public void listarPorDisponibilidad(boolean disp) {
        libros.stream().filter(l -> l.isDisponible()==disp)
                .forEach(System.out::println);
    }

    // ====== Usuarios ======
    public void registrarUsuario(Usuario u) { usuarios.add(u); }

    // ====== Persistencia básica ======
    public void guardar() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/libros.txt"))) {
            for (Libro l : libros) bw.write(l.toCSV() + System.lineSeparator());
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/usuarios.txt"))) {
            for (Usuario u : usuarios) bw.write(u.toCSV() + System.lineSeparator());
        }
        System.out.println("Datos guardados.");
    }
    public void cargar() throws IOException {
        libros.clear(); usuarios.clear();
        File fLib = new File("data/libros.txt");
        if (fLib.exists()) try (BufferedReader br = new BufferedReader(new FileReader(fLib))) {
            String line; while ((line = br.readLine()) != null) libros.add(Libro.fromCSV(line));
        }
        File fUsr = new File("data/usuarios.txt");
        if (fUsr.exists()) try (BufferedReader br = new BufferedReader(new FileReader(fUsr))) {
            String line; while ((line = br.readLine()) != null) usuarios.add(Usuario.fromCSV(line));
        }
        System.out.println("Datos cargados.");
    }
}
