package dgtic.core.service.jpa;

import dgtic.core.entity.Cliente;
import dgtic.core.entity.Comentario;
import dgtic.core.entity.Producto;
import dgtic.core.repository.ClienteRepository;
import dgtic.core.repository.ComentarioRepository;
import dgtic.core.service.IClienteService;
import dgtic.core.service.IComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService implements IComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Page<Comentario> findAll(Pageable pageable) {
        return comentarioRepository.findAll(pageable);
    }

    @Override
    public void guardar(Comentario comentario) {
        comentarioRepository.save(comentario);
    }

    @Override
    public void borrar(Integer id) {
        comentarioRepository.deleteById(id);
    }

    @Override
    public Comentario buscarComentario(Integer id) {
        Optional<Comentario> op = comentarioRepository.findById(id);
        return op.get();
    }

    @Override
    public List<Comentario> buscarComentarios() {
        return comentarioRepository.findAll();
    }

    @Override
    public List<Comentario> buscarComentariosPorProducto(Producto producto) {
        return comentarioRepository.findByProducto(producto);
    }
}
