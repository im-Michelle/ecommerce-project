package dgtic.core.service.jpa;

import dgtic.core.entity.Estado;
import dgtic.core.repository.EstadoRepository;
import dgtic.core.service.IEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService implements IEstadoService {
    @Autowired
    EstadoRepository estadoRepository;

    @Override
    public Page<Estado> findAll(Pageable pageable) {
        return estadoRepository.findAll(pageable);
    }

    @Override
    public void guardar(Estado estado) {
        estadoRepository.save(estado);
    }

    @Override
    public void borrar(Integer id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public Estado buscarEstado(Integer id) {
        Optional<Estado> op = estadoRepository.findById(id);
        return op.get();
    }

    @Override
    public List<Estado> buscarEstados() {
        return estadoRepository.findAll();
    }
}
