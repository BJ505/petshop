package petshop.petshop.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import petshop.petshop.model.Productos;
import petshop.petshop.model.Ventas;
import petshop.petshop.service.ProductosService;
import petshop.petshop.service.VentasService;



@RestController
public class petshopController {    
    @Autowired
    private VentasService ventasService;

    @Autowired
    private ProductosService productosService;

    //---------------VENTAS----------------
    @GetMapping("/ventas")
    public List<Ventas> getVentas() {
        return ventasService.getVentas();
    }

    //Una venta en especifico (id)
    @GetMapping("/ventas/buscar/{id}")
    public Optional<Ventas> getVentasId(@PathVariable Long id) {
        return ventasService.getVentaId(id);
    }

    @PostMapping("/ventas")
    public Ventas createVenta(@RequestBody Ventas publicacion) {
        return ventasService.createVenta(publicacion);
    }

    @PutMapping("/ventas/{id}")
    public Ventas updateVenta(@PathVariable Long id, @RequestBody Ventas publicacion) {
        return ventasService.updateVenta(id, publicacion);
    }

    @DeleteMapping("/ventas/{id}")
    public void deleteVenta(@PathVariable Long id) {
        ventasService.deleteVenta(id);
    }

    //---------------PRODUCTOS----------------
    @GetMapping("/productos")
    public List<Productos> geProductos() {
        return productosService.getProductos();
    }

    //Un producto en especifico (id)
    @GetMapping("/productos/buscar/{id}")
    public Optional<Productos> getProductoId(@PathVariable Long id) {
        return productosService.getProductoId(id);
    }

    @PostMapping("/productos/{idVenta}")
    public Productos createProducto(@PathVariable Long idVenta, @RequestBody Productos producto) {
        return productosService.createProducto(idVenta,producto);
    }

    @PutMapping("/productos/{idProducto}/{idVenta}")
    public Productos updateProducto(@PathVariable Long idProducto, @PathVariable Long idVenta, @RequestBody Productos producto) {
        return productosService.updateProducto(idProducto, idVenta, producto); 
    }

    @DeleteMapping("/productos/{id}")
    public void deleteProducto(@PathVariable Long id){
        productosService.deleteProductos(id);
    }

    // @PutMapping("/productos/{id}")
    // public Ventas updateVenta(@PathVariable Long id, @RequestBody Ventas publicacion) {
    //     return productosService.updateVenta(id, publicacion);
    // }

    // @DeleteMapping("/productos/{id}")
    // public void deleteVenta(@PathVariable Long id) {
    //     productosService.deleteVenta(id);
    // }


    //Todas las ventas por día
    // @GetMapping("/ventas/porfecha/{dia}")
    // public List<Ventas> getVentasPorFecha(@PathVariable String dia) {
    //     //Inicializamos List que tendrá toda las ventas encontradas para la fecha señalada
    //     List<Ventas> ventasPorFecha = new ArrayList<Ventas>();
    //     for (Ventas venta : ventas){
    //         if (venta.getFechaVenta().equals(dia)){
    //             //Agregamos a la nueva List la venta encontrada
    //             ventasPorFecha.add(venta);
    //         }
    //     }
    //     return ventasPorFecha;
    // }

    //Total por día
    // @GetMapping("/ventas/totalfecha/{dia}")
    // public int getVentasTotalPorFecha(@PathVariable String dia) {
    //     int total = 0;
    //     for (Ventas venta : ventas){
    //         if (venta.getFechaVenta().equals(dia)){
    //             List<Productos> productosventa = venta.getProductos();
    //             //sumamos el total de la venta a la variable totalxfecha
    //             for (Productos producto : productosventa){
    //                 total += producto.getPrecio() * producto.getCantidad();
    //             }
    //         }
    //     }
    //     return total;
    // }

}
