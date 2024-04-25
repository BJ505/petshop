package petshop.petshop.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public CollectionModel<EntityModel<Ventas>> getVentas() {
        List<Ventas> ventas = ventasService.getVentas();
        List<EntityModel<Ventas>> ventaResorurces = ventas.stream()
            .map( venta -> EntityModel.of(venta,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentasId(venta.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentas());
        CollectionModel<EntityModel<Ventas>> resources = CollectionModel.of(ventaResorurces, linkTo.withRel("publicaciones"));

        return resources;
    }

    //Una venta en especifico (id)
    @GetMapping("/ventas/buscar/{id}")
    public EntityModel<Ventas> getVentasId(@PathVariable Long id) {
        Optional<Ventas> venta = ventasService.getVentaId(id);

        if(venta.isPresent()){
            return EntityModel.of(venta.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentasId(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentas()).withRel("ventas"));
        } else{
            throw new VentasNotFoundException("Publicacion no encontrada con el id: " + id);
        }
    }

    @PostMapping("/ventas")
    public EntityModel<Ventas> createVenta(@RequestBody Ventas venta) {
        Ventas createVentas = ventasService.createVenta(venta);
        return EntityModel.of(createVentas,
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentasId(createVentas.getId())).withSelfRel(),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentas()).withRel("ventas"));
    }

    @PutMapping("/ventas/{id}")
    public EntityModel<Ventas> updateVenta(@PathVariable Long id, @RequestBody Ventas venta) {
        Ventas updateVentas = ventasService.updateVenta(id, venta);

        return EntityModel.of(updateVentas,
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentasId(updateVentas.getId())).withSelfRel(),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentas()).withRel("ventas"));
    }

    @DeleteMapping("/ventas/{id}")
    public void deleteVenta(@PathVariable Long id) {
        ventasService.deleteVenta(id);
    }

    //---------------PRODUCTOS----------------
    @GetMapping("/productos")
    public CollectionModel<EntityModel<Productos>> geProductos() {
        List<Productos> productos = productosService.getProductos();
        List<EntityModel<Productos>> productosResources = productos.stream()
            .map( producto -> EntityModel.of(producto,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductoId(producto.getId())).withSelfRel()
        ))
        .collect(Collectors.toList());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).geProductos());
        CollectionModel<EntityModel<Productos>> resources = CollectionModel.of(productosResources, linkTo.withRel("productos"));

        return resources;
    }

    //Un producto en especifico (id)
    @GetMapping("/productos/buscar/{id}")
    public EntityModel<Productos> getProductoId(@PathVariable Long id) {
        Optional<Productos> producto = productosService.getProductoId(id);

        if(producto.isPresent()){
            return EntityModel.of(producto.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductoId(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).geProductos()).withRel("productos"));
        }else{
            throw new VentasNotFoundException("Producto no encontrado con el id: " + id);
        }
    }

    @PostMapping("/productos/{idVenta}")
    public EntityModel<Productos> createProducto(@PathVariable Long idVenta, @RequestBody Productos producto) {
        Productos createProducto = productosService.createProducto(idVenta,producto);
        return EntityModel.of(createProducto,
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductoId(createProducto.getId())).withSelfRel(),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).geProductos()).withRel("productos"));
    }

    @PutMapping("/productos/{idProducto}/{idVenta}")
    public EntityModel<Productos> updateProducto(@PathVariable Long idProducto, @PathVariable Long idVenta, @RequestBody Productos producto) {
        Productos productoUpdate = productosService.updateProducto(idProducto, idVenta, producto);
        return EntityModel.of(productoUpdate,
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductoId(productoUpdate.getId())).withSelfRel(),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).geProductos()).withRel("productos"));
    }

    @DeleteMapping("/productos/{id}")
    public void deleteProducto(@PathVariable Long id){
        productosService.deleteProductos(id);
    }
}
