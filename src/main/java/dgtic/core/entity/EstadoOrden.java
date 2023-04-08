package dgtic.core.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "estado_orden")
public class EstadoOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_orden")
    private Integer id_estado_orden;
    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;
    private Date fecha_estado;
    @ManyToOne
    @JoinColumn(name = "id_orden")
    private Orden orden;

    public Integer getId_estado_orden() {
        return id_estado_orden;
    }

    public void setId_estado_orden(Integer id_estado_orden) {
        this.id_estado_orden = id_estado_orden;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFecha_estado() {
        return fecha_estado;
    }

    public void setFecha_estado(Date fecha_estado) {
        this.fecha_estado = fecha_estado;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "EstadoOrden{" +
                "id_estado_orden=" + id_estado_orden +
                ", fecha_estado=" + fecha_estado +
                '}';
    }
}
