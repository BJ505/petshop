package petshop.petshop;

public class Productos {
    private int idProducto;
    private String nombre;
    private int precio;
    private int cantidad;

    public Productos(int idProducto, String nombre, int precio, int cantidad) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    //Getters
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }
}
