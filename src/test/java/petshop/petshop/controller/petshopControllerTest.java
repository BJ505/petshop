package petshop.petshop.controller;

import static org.mockito.Mockito.when;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import petshop.petshop.model.Productos;
import petshop.petshop.model.Ventas;
import petshop.petshop.service.ProductosServiceImpl;
import petshop.petshop.service.VentasServiceImpl;

@WebMvcTest(petshopController.class)
public class petshopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentasServiceImpl ventasServiceMock;

    @MockBean
    private ProductosServiceImpl productosServiceMock;

    @BeforeAll
    public static void open(){
        System.out.println("Iniciando pruebas de PetshopControllerTest");
    }

    //Get all ventas
    @Test
    public void getAllVentasTest() throws Exception {
        Ventas venta1 = new Ventas();
        venta1.setFechaVenta("23-04-2024");
        venta1.setVendedor("Byron Jaramillo");
        venta1.setId(1L);

        Ventas venta2 = new Ventas();
        venta2.setFechaVenta("24-04-2024");
        venta2.setVendedor("Javiera Jaramillo");
        venta2.setId(2L);

        List<Ventas> ventas = List.of(venta1, venta2);
        when(ventasServiceMock.getVentas()).thenReturn(ventas);

        mockMvc.perform(MockMvcRequestBuilders.get("/ventas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.ventasList.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.ventasList[0].vendedor").value("Byron Jaramillo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.ventasList[1].vendedor").value("Javiera Jaramillo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.ventasList[0].fechaVenta").value("23-04-2024"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.ventasList[1].fechaVenta").value("24-04-2024"));
    }

    //Get all Productos
    @Test
    public void getAllProductosTest() throws Exception {
        Productos producto1 = new Productos();
        producto1.setId(1L);
        producto1.setNombre("Comida Gato 10k");
        producto1.setPrecio(20000);
        producto1.setCantidad(2);

        Productos producto2 = new Productos();
        producto2.setId(2L);
        producto2.setNombre("Comida Gato 5k");
        producto2.setPrecio(13000);
        producto2.setCantidad(1);

        List<Productos> productos = List.of(producto1, producto2);
        when(productosServiceMock.getProductos()).thenReturn(productos);

        mockMvc.perform(MockMvcRequestBuilders.get("/productos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.productosList.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.productosList[0].nombre").value("Comida Gato 10k"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.productosList[1].nombre").value("Comida Gato 5k"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.productosList[0].precio").value(20000))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.productosList[1].precio").value(13000));
    // }
    }

}
