package petshop.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import petshop.petshop.model.Productos;

public interface ProductosRepository extends JpaRepository<Productos,Long> {

    
}
