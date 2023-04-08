package dgtic.core.entity;

import dgtic.core.model.DetalleOrden;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "ordenes")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_orden;

    private Date fecha_orden;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalleOrdenes = new ArrayList<>();

    public Orden() {
    }

    public Integer getId_orden() {
        return id_orden;
    }

    public void setId_orden(Integer id_orden) {
        this.id_orden = id_orden;
    }

    public Date getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(Date fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public List<DetalleOrden> getDetalleOrdenes() {
        return detalleOrdenes;
    }

    public void setDetalleOrdenes(List<DetalleOrden> detalleOrdenes) {
        this.detalleOrdenes = detalleOrdenes;
    }

    public double calcularTotal() {
        double total = 0;
        for (DetalleOrden detalle : detalleOrdenes) {
            total += detalle.calcularTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id_orden=" + id_orden +
                ", fecha_orden=" + fecha_orden +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orden orden = (Orden) o;
        return id_orden.equals(orden.id_orden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_orden);
    }
}
