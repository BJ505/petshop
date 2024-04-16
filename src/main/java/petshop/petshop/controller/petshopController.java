package petshop.petshop.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class petshopController {
    

    public int getTotal(int[] precios, int[] cantidad) {
        int subtotal = 0;
        for (int i = 0; i < precios.length; i++) {
            subtotal += precios[i] * cantidad[i];
        }
        return subtotal;
    }
    //Todas las ventas
    @GetMapping("/ventas")
    public List<Ventas> getVentas() {
        return ventas;
    }

    //Una venta en especifico (id)
    @GetMapping("/ventas/buscar/{id}")
    public Ventas getVentasId(@PathVariable int id) {
        for (Ventas venta : ventas){
            if (venta.getId() == id){
                return venta;
            }
        }
        return null;
    }

    //Todas las ventas por día
    @GetMapping("/ventas/porfecha/{dia}")
    public List<Ventas> getVentasPorFecha(@PathVariable String dia) {
        //Inicializamos List que tendrá toda las ventas encontradas para la fecha señalada
        List<Ventas> ventasPorFecha = new ArrayList<Ventas>();
        for (Ventas venta : ventas){
            if (venta.getFechaVenta().equals(dia)){
                //Agregamos a la nueva List la venta encontrada
                ventasPorFecha.add(venta);
            }
        }
        return ventasPorFecha;
    }

    //Total por día
    @GetMapping("/ventas/totalfecha/{dia}")
    public int getVentasTotalPorFecha(@PathVariable String dia) {
        int total = 0;
        for (Ventas venta : ventas){
            if (venta.getFechaVenta().equals(dia)){
                List<Productos> productosventa = venta.getProductos();
                //sumamos el total de la venta a la variable totalxfecha
                for (Productos producto : productosventa){
                    total += producto.getPrecio() * producto.getCantidad();
                }
            }
        }
        return total;
    }

}
