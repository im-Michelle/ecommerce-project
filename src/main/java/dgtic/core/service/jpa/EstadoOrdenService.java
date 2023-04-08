package dgtic.core.service.jpa;

import dgtic.core.entity.EstadoOrden;
import dgtic.core.repository.EstadoOrdenRepository;
import dgtic.core.service.IEstadoOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoOrdenService  implements IEstadoOrdenService {
    @Autowired
    EstadoOrdenRepository estadoOrdenRepository;
    @Override
    public Page<EstadoOrden> findAll(Pageable pageable) {
        return estadoOrdenRepository.findAll(pageable);
    }

    @Override
    public void guardar(EstadoOrden estadoOrden) {
        estadoOrdenRepository.save(estadoOrden);
    }

    @Override
    public void borrar(Integer id) {
        estadoOrdenRepository.deleteById(id);
    }

    @Override
    public EstadoOrden buscarEstadoOrden(Integer id) {
        Optional<EstadoOrden> op = estadoOrdenRepository.findById(id);
        return op.get();
    }

    @Override
    public List<EstadoOrden> buscarEstadoOrdenes() {
        return estadoOrdenRepository.findAll();
    }
}
