package dgtic.core.service;

import dgtic.core.entity.Cliente;
import dgtic.core.entity.Comentario;
import dgtic.core.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IComentarioService {
    public Page<Comentario> findAll(Pageable pageable);
    public void guardar(Comentario comentario);
    public void borrar(Integer id);
    public Comentario buscarComentario(Integer id);
    List<Comentario> buscarComentarios();
    List<Comentario> buscarComentariosPorProducto(Producto producto);
}
