package dgtic.core.service;

import dgtic.core.entity.Orden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IOrdenService {
    public Page<Orden> findAll(Pageable pageable);
    public void guardar(Orden orden);
    public void borrar(Integer id);
    public Orden buscarOrden(Integer id);
    List<Orden> buscarOrdenes();

    Optional<Orden> buscarOrdenes(Integer dato);
}
