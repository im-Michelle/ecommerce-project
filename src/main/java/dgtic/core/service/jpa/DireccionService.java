package dgtic.core.service.jpa;

import dgtic.core.entity.Cliente;
import dgtic.core.entity.Direccion;
import dgtic.core.repository.DireccionRepository;
import dgtic.core.service.IDireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionService implements IDireccionService {
    @Autowired
    private DireccionRepository direccionRepository;
    @Override
    public Page<Direccion> findAll(Pageable pageable) {
        return direccionRepository.findAll(pageable);
    }

    @Override
    public void guardar(Direccion direccion) {
        direccionRepository.save(direccion);
    }

    @Override
    public void borrar(Integer id) {
        direccionRepository.deleteById(id);
    }

    @Override
    public Direccion buscarDireccion(Integer id) {
        Optional<Direccion> op = direccionRepository.findById(id);
        return op.get();
    }

    @Override
    public List<Direccion> buscarDirecciones() {
        return direccionRepository.findAll();
    }
}
