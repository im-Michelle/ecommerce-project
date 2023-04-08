package dgtic.core.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetalleOrdenId implements Serializable {

    @Column(name = "id_orden")
    private Integer idOrden;

    @Column(name = "id_producto")
    private Integer idProducto;

    public DetalleOrdenId() {
    }

    public DetalleOrdenId(Integer idOrden, Integer idProducto) {
        this.idOrden = idOrden;
        this.idProducto = idProducto;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleOrdenId that = (DetalleOrdenId) o;
        return idOrden.equals(that.idOrden) && idProducto.equals(that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrden, idProducto);
    }
}
