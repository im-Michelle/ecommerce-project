package dgtic.core.service.jpa;

import dgtic.core.entity.Autorizacion;
import dgtic.core.repository.AutorizacionRepository;
import dgtic.core.service.IAutorizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AutorizacionService implements IAutorizacionService {

    @Autowired
    AutorizacionRepository autorizacionRepository;

    @Override
    public Autorizacion buscarCategoria(String id) {
        Optional<Autorizacion> op = autorizacionRepository.findById(id);
        return op.get();
    }

    @Override
    public void guardar(Autorizacion autorizacion) {
        autorizacionRepository.save(autorizacion);
    }

}
