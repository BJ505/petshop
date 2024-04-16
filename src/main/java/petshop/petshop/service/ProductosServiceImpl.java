package petshop.petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petshop.petshop.model.Productos;
import petshop.petshop.model.Ventas;
import petshop.petshop.repository.ProductosRepository;
import petshop.petshop.repository.VentasRepository;

@Service
public class ProductosServiceImpl implements ProductosService{
    @Autowired
    private ProductosRepository productosRepository;
    @Autowired
    private VentasRepository ventasRepository;

    @Override
    public List<Productos> getProductos() {
        return productosRepository.findAll();
    }

    @Override
    public Optional<Productos> getProductoId(Long id) {
        return productosRepository.findById(id);
    }

    @Override
    public Productos createProducto(Long id, Productos productos) {
        if(ventasRepository.existsById(id)){
            Ventas ventaData = ventasRepository.getReferenceById(id);
            productos.setVenta(ventaData);
            return productosRepository.save(productos);
        }else{
            return null;
        }
    }

    @Override
    public Productos updateProducto(Long idProducto, Long idVenta, Productos productos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProducto'");
    }

    @Override
    public void deleteProductos(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProductos'");
    }
    
}
