package ProgramacionII.RegistroII.Semana8;

public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private int anio;
    private String genero;
    private boolean disponible = true;

    public Libro(int id, String titulo, String autor, int anio, String genero) {
        this.id = id; this.titulo = titulo; this.autor = autor;
        this.anio = anio; this.genero = genero;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnio() { return anio; }
    public String getGenero() { return genero; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean d) { this.disponible = d; }

    public String toString() {
        return id + " | " + titulo + " | " + autor + " | " + anio + " | " + genero + " | " + (disponible?"Disponible":"Prestado");
    }

    public String toCSV() {
        return id + ";" + titulo + ";" + autor + ";" + anio + ";" + genero + ";" + disponible;
    }
    public static Libro fromCSV(String line) {
        String[] x = line.split(";");
        Libro l = new Libro(Integer.parseInt(x[0]), x[1], x[2], Integer.parseInt(x[3]), x[4]);
        l.setDisponible(Boolean.parseBoolean(x[5]));
        return l;
    }
}

