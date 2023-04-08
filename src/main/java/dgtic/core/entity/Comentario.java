package dgtic.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_comentario;
    private String texto_comentario;
    private Date fecha_comentario;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente = new Cliente();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto= new Producto();

    public Comentario() {
    }

    public Integer getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(Integer id_comentario) {
        this.id_comentario = id_comentario;
    }

    public String getTexto_comentario() {
        return texto_comentario;
    }

    public void setTexto_comentario(String texto_comentario) {
        this.texto_comentario = texto_comentario;
    }

    public Date getFecha_comentario() {
        return fecha_comentario;
    }

    public void setFecha_comentario(Date fecha_comentario) {
        this.fecha_comentario = fecha_comentario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
