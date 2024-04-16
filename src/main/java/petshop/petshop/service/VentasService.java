package petshop.petshop.service;

import java.util.List;
import java.util.Optional;

import petshop.petshop.model.Ventas;

public interface VentasService {
    List<Ventas> getVentas();
    Optional<Ventas> getVentaId(Long id);
    Ventas createVenta(Ventas Venta);
    Ventas updateVenta(Long idVenta, Ventas venta);
    void deleteVenta(Long id);
}
