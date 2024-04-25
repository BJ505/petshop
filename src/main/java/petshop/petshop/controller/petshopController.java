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
}
