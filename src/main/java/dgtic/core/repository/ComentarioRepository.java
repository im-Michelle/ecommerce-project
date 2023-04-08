package dgtic.core.repository;

import dgtic.core.entity.Autorizacion;
import dgtic.core.entity.Comentario;
import dgtic.core.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario,Integer> {
    List<Comentario> findByProducto(Producto producto);
}
