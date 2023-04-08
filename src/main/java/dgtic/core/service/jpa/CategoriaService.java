package dgtic.core.service.jpa;

import dgtic.core.entity.Categoria;
import dgtic.core.repository.CategoriaRepository;
import dgtic.core.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoriaService implements ICategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public Page<Categoria> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    @Override
    public void guardar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public void borrar(Integer id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public Categoria buscarCategoria(Integer id) {
        Optional<Categoria> op = categoriaRepository.findById(id);
        return op.get();
    }

    @Override
    public List<Categoria> buscarCategorias() {
        return categoriaRepository.findAll();
    }
}
