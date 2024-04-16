package petshop.petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petshop.petshop.model.Ventas;
import petshop.petshop.repository.VentasRepository;

@Service
public class VentasServiceImpl implements VentasService{
    @Autowired
    private VentasRepository ventasRepository;

    @Override
    public List<Ventas> getVentas(){
        return ventasRepository.findAll();
    }

    @Override
    public Optional<Ventas> getVentaId(Long id) {
        return ventasRepository.findById(id);
    }

    @Override
    public Ventas createVentas(Ventas Venta) {
        return ventasRepository.save(Venta);
    }

    @Override
    public Ventas updateVentas(Long idVenta, Ventas venta) {
        if(ventasRepository.existsById(idVenta)){
            return ventasRepository.save(venta);
        }else{
            return null;
        }
    }

    @Override
    public void deleteVentas(Long id) {
        if(ventasRepository.existsById(id)){
            ventasRepository.deleteById(id);
        }
    }
}
