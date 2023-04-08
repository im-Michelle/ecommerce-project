package dgtic.core.service;

import dgtic.core.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoriaService {
    public Page<Categoria> findAll(Pageable pageable);
    public void guardar(Categoria categoria);
    public void borrar(Integer id);
    public Categoria buscarCategoria(Integer id);
    List<Categoria> buscarCategorias();
}
