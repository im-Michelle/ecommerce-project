package dgtic.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "estados")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_estado;

    private String nombre;

    public Estado() {
    }

    public Estado(Integer id_estado, String nombre) {
        this.id_estado = id_estado;
        this.nombre = nombre;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id_estado=" + id_estado +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
