package dgtic.core.service.jpa;

import dgtic.core.entity.Orden;
import dgtic.core.repository.OrdenRepository;
import dgtic.core.service.IOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService implements IOrdenService {
    @Autowired
    OrdenRepository ordenRepository;
    @Override
    public Page<Orden> findAll(Pageable pageable) {
        return ordenRepository.findAll(pageable);
    }

    @Override
    public void guardar(Orden orden) {
        ordenRepository.save(orden);
    }

    @Override
    public void borrar(Integer id) {
        ordenRepository.deleteById(id);
    }

    @Override
    public Orden buscarOrden(Integer id) {
        Optional<Orden> op = ordenRepository.findById(id);
        return op.get();
    }

    @Override
    public List<Orden> buscarOrdenes() {
        return ordenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Orden> buscarOrdenes(Integer dato) {
        return ordenRepository.findById(dato);
    }
}
