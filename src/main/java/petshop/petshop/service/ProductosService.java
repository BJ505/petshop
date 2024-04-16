package petshop.petshop.service;

import java.util.List;
import java.util.Optional;

import petshop.petshop.model.Productos;

public interface ProductosService {
    List<Productos> getProductos();
    Optional<Productos> getProductoId(Long id);
    Productos createProducto(Long id,Productos productos);
    Productos updateProducto(Long idProducto, Long idVenta, Productos productos);
    void deleteProductos(Long id);
}
