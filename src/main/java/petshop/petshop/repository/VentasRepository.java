package petshop.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import petshop.petshop.model.Ventas;

public interface VentasRepository extends JpaRepository<Ventas,Long> {

    
}
