package petshop.petshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import petshop.petshop.model.Productos;
import petshop.petshop.model.Ventas;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductosRepositoryTest {
    @Autowired
    private ProductosRepository productosRepository;

    @BeforeAll
    public static void open(){
        System.out.println("Iniciando pruebas de ProductosRepositoryTest");
    }

    @Test
    public void testGuardarProductos(){
        Ventas venta = new Ventas();
        //Productos de id (tipo long) 2
        venta.setId(2L);
        
        Productos producto = new Productos();
        producto.setNombre("Comida perro 18K");
        producto.setPrecio(22000);
        producto.setCantidad(1);
        producto.setVenta(venta);

        Productos result = productosRepository.save(producto);

        assertNotNull(result.getId());
        assertEquals("Comida perro 18K", result.getNombre());
    }

    @AfterAll
    public static void close(){
        System.out.println("Han terminado todas las pruebas de ProductosRepositoryTest");
    }
}
