package dgtic.core.service.jpa;

import dgtic.core.model.DetalleOrden;
import dgtic.core.repository.DetalleOrdenRepository;
import dgtic.core.service.IDetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenService implements IDetalleOrdenService {
    @Autowired
    DetalleOrdenRepository detalleOrdenRepository;
    @Override
    public Page<DetalleOrden> findAll(Pageable pageable) {
        return detalleOrdenRepository.findAll(pageable);
    }

    @Override
    public void guardar(DetalleOrden detalleOrden) {
        detalleOrdenRepository.save(detalleOrden);
    }

    @Override
    public void borrar(Integer id) {
        detalleOrdenRepository.deleteById(id);
    }

    @Override
    public DetalleOrden buscarDetalleOrden(Integer id) {
        Optional<DetalleOrden> op = detalleOrdenRepository.findById(id);
        return op.get();
    }

    @Override
    public List<DetalleOrden> buscarDetalleOrdenes() {
        return detalleOrdenRepository.findAll();
    }
}
