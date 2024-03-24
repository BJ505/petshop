package petshop.petshop;

import java.util.List;

public class Ventas {
    private int id;
    private String fechaVenta;
    private List<Productos> productos;
    private int total;

    public Ventas(int id, String fechaVenta, List<Productos> productos, int total){
        this.id = id;
        this.fechaVenta = fechaVenta;
        this.productos = productos;
        this.total = total;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public int getTotal() {
        return total;
    }
}
