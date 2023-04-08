package dgtic.core.model;

import dgtic.core.entity.Orden;
import dgtic.core.entity.Producto;

import javax.persistence.*;

@Entity
@Table(name = "detalle_orden")
public class DetalleOrden {

    @EmbeddedId
    private DetalleOrdenId id = new DetalleOrdenId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id_orden")
    @JoinColumn(name = "id_orden")
    private Orden orden;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    public DetalleOrden() {
    }

    public DetalleOrdenId getId() {
        return id;
    }

    public void setId(DetalleOrdenId id) {
        this.id = id;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double calcularTotal() {
        return this.cantidad * this.producto.getPrecio();
    }

    @Override
    public String toString() {
        return "DetalleOrden{" +
                "id=" + id +
                ", orden=" + orden +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }

}
