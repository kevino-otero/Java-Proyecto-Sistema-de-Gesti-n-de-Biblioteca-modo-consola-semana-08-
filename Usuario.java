public class Usuario {
    private int id;
    private String nombre;
    private String identificacion;

    public Usuario(int id, String nombre, String identificacion) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String toCSV() {
        return id + ";" + nombre + ";" + identificacion;
    }

    public static Usuario fromCSV(String line) {
        String[] campos = line.split(";");
        return new Usuario(
            Integer.parseInt(campos[0]), 
            campos[1], 
            campos[2]
        );
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " | " + identificacion;
    }
}
