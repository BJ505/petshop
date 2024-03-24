package petshop.petshop;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class petshopController {
    private List<Ventas> ventas = new ArrayList<>();

    public petshopController() {
        //Día 01-03-2024
        ventas.add(new Ventas(1, "01-03-2024",
            Arrays.asList(
                new Productos(5,"Sobre comida 1", 1800,2),
                new Productos(1,"Arena de gato",10250, 1)
            ), 
            getTotal(new int[]{1800,10250}, new int[]{2,1}))//Mas adelante ver la forma de tomar los datos directo de la venta
        );
        ventas.add(new Ventas(2, "01-03-2024",
            Arrays.asList(
                new Productos(3,"Regalo dulce canino",500, 3),
                new Productos(6,"Collar",4000, 1),
                new Productos(7,"Shampoo pequeño",1750, 1)
            ), 
            getTotal(new int[]{500,4000,1750}, new int[]{3,1,1}))
        );
        ventas.add(new Ventas(3, "01-03-2024",
            Arrays.asList(
                new Productos(5,"Sobre comida 1",1800, 3),
                new Productos(8,"Juguete perro 1",7500, 1),
                new Productos(2,"Shampoo grande",3500, 1)
            ), 
            getTotal(new int[]{1800,7500,3300}, new int[]{3,1,1}))
        );

        //Día 02-03-2024
        ventas.add(new Ventas(4, "02-03-2024",
            Arrays.asList(
                new Productos(9,"Alimento de perro MasterDog", 21500,1),
                new Productos(10,"Collar 2",4500, 1)
            ), 
            getTotal(new int[]{21500,4500}, new int[]{1,1}))//Mas adelante ver la forma de tomar los datos directo de la venta
        );
        ventas.add(new Ventas(5, "02-03-2024",
            Arrays.asList(
                new Productos(8,"Juguete perro 1",7500, 2),
                new Productos(5,"Sobre comida 1",1800, 6),
                new Productos(11,"Sobre comida 2",2000, 4),
                new Productos(12,"Collar 3",5000, 2)
            ), 
            getTotal(new int[]{7500,1800,2000,5000}, new int[]{2,6,4,2}))
        );
        ventas.add(new Ventas(6, "02-03-2024",
            Arrays.asList(
                new Productos(9,"Alimento de perro MasterDog", 21500,1),
                new Productos(7,"Shampoo pequeño",1750, 1),
                new Productos(12,"Collar 3",5000, 1)
            ), 
            getTotal(new int[]{21500,1750,5000}, new int[]{1,1,1}))
        );
    };

    public int getTotal(int[] precios, int[] cantidad) {
        int subtotal = 0;
        for (int i = 0; i < precios.length; i++) {
            subtotal += precios[i] * cantidad[i];
        }
        return subtotal;
    }
    //Todas las ventas
    @GetMapping("/ventas")
    public List<Ventas> getVentas() {
        return ventas;
    }

    //Una venta en especifico (id)
    @GetMapping("/ventas/buscar/{id}")
    public Ventas getVentasId(@PathVariable int id) {
        for (Ventas venta : ventas){
            if (venta.getId() == id){
                return venta;
            }
        }
        return null;
    }

    //Todas las ventas por día
    @GetMapping("/ventas/porfecha/{dia}")
    public List<Ventas> getVentasPorFecha(@PathVariable String dia) {
        //Inicializamos List que tendrá toda las ventas encontradas para la fecha señalada
        List<Ventas> ventasPorFecha = new ArrayList<Ventas>();
        for (Ventas venta : ventas){
            if (venta.getFechaVenta().equals(dia)){
                //Agregamos a la nueva List la venta encontrada
                ventasPorFecha.add(venta);
            }
        }
        return ventasPorFecha;
    }

    //Total por día
    @GetMapping("/ventas/totalfecha/{dia}")
    public int getVentasTotalPorFecha(@PathVariable String dia) {
        int total = 0;
        for (Ventas venta : ventas){
            if (venta.getFechaVenta().equals(dia)){
                List<Productos> productosventa = venta.getProductos();
                //sumamos el total de la venta a la variable totalxfecha
                for (Productos producto : productosventa){
                    total += producto.getPrecio() * producto.getCantidad();
                }
            }
        }
        return total;
    }

}
