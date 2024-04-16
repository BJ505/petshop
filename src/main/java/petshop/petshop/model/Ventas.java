package petshop.petshop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Ventas")
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "fechaVenta")
    private String fechaVenta;
    /*El caso ideal que hubiese aplicado en un ambito profesional
    es que esta relación sea manytomany con una tabla intermediaria entre Ventas y Productos (un ventas_detalle) para manejar de manera mas lógica
    Lo que es el atributo CANTIDAD de cada producto para cada venta. (Por tiempo no aplicaré esto ahora, espero tener la oportunidad
    de ejecutar la solución en una próxima entrega)*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "venta")
    // @JsonIgnoreProperties("venta")
    private List<Productos> productos;
    @Transient
    @JsonProperty
    private int total;

    //Getters and setters
    public Long getId() {
        return id;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public int getTotal() {
        return total;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
