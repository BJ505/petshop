package petshop.petshop.service;

import java.util.List;
import java.util.Optional;

import petshop.petshop.model.Ventas;

public interface VentasService {
    List<Ventas> getVentas();
    Optional<Ventas> getVentaId(Long id);
    Ventas createVentas(Ventas Venta);
    Ventas updateVentas(Long idVenta, Ventas venta);
    void deleteVentas(Long id);
}
