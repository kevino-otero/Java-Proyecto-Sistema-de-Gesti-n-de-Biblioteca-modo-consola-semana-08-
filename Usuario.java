package ProgramacionII.RegistroII.Semana8;

public class Usuario {
    private int id;
    private String nombre;
    private String identificacion;

    public Usuario(int id, String nombre, String identificacion) {
        this.id = id; this.nombre = nombre; this.identificacion = identificacion;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getIdentificacion() { return identificacion; }

    public String toCsv() { return id + ";" + nombre + ";" + identificacion; }
    public static Usuario fromCsv(String line) {
        String[] x = line.split(";");
        return new Usuario(Integer.parseInt(x[0]), x[1], x[2]);
    }

    @Override
    public String toString() { return id + " | " + nombre + " | " + identificacion; }

    // Método agregado
    public boolean esValido() {
        return id > 0 && nombre != null && identificacion != null;
    }
}
 