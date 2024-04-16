package petshop.petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petshop.petshop.model.Productos;
import petshop.petshop.model.Ventas;
import petshop.petshop.repository.VentasRepository;

@Service
public class VentasServiceImpl implements VentasService{
    @Autowired
    private VentasRepository ventasRepository;

    @Override
    public List<Ventas> getVentas(){
        List<Ventas> newFindAll = ventasRepository.findAll();
        int totalVenta = 0;
        for (Ventas element : newFindAll) {
            totalVenta = 0;
            for (Productos ele : element.getProductos()){

                totalVenta += (ele.getPrecio() * ele.getCantidad());
            }
            if(totalVenta != 0){
                element.setTotal(totalVenta);
            }else{
                element.setTotal(0);
            }
        }
        return newFindAll;
    }

    @Override
    public Optional<Ventas> getVentaId(Long id) {
        
        Optional<Ventas> getById = ventasRepository.findById(id);
        int totalVenta = 0;
        for (Productos ele : getById.get().getProductos()){

            totalVenta += (ele.getPrecio() * ele.getCantidad());
        }
        if(totalVenta != 0){
            getById.get().setTotal(totalVenta);
        }else{
            getById.get().setTotal(0);
        }
        return getById;
    }

    @Override
    public Ventas createVenta(Ventas Venta) {
        return ventasRepository.save(Venta);
    }

    @Override
    public Ventas updateVenta(Long idVenta, Ventas venta) {
        if(ventasRepository.existsById(idVenta)){
            venta.setId(idVenta);
            return ventasRepository.save(venta);
        }else{
            return null;
        }
    }

    @Override
    public void deleteVenta(Long id) {
        if(ventasRepository.existsById(id)){
            ventasRepository.deleteById(id);
        }
    }
}
