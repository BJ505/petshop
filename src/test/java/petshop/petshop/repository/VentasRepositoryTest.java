package petshop.petshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

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
public class VentasRepositoryTest {
    @Autowired
    private VentasRepository ventasRepository;
    
    @BeforeAll
    public static void open(){
        System.out.println("Iniciando pruebas de VentasRepositoryTest");
    }

    @Test
    public void guardarVentasTest() {
        Ventas venta = new Ventas();
        venta.setFechaVenta("23-04-2024");
        venta.setVendedor("Byron Jaramillo");
        
        Ventas result = ventasRepository.save(venta);

        assertNotNull(result.getId());
        assertEquals("Byron Jaramillo", result.getVendedor());
    }

    @AfterAll
    public static void close(){
        System.out.println("Han terminado todas las pruebas de PublicacionRepositoryTest");
    }
}
