package dgtic.core.service;

import dgtic.core.entity.Direccion;
import dgtic.core.entity.EstadoOrden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEstadoOrdenService {
    public Page<EstadoOrden> findAll(Pageable pageable);
    public void guardar(EstadoOrden estadoOrden);
    public void borrar(Integer id);
    public EstadoOrden buscarEstadoOrden(Integer id);
    List<EstadoOrden> buscarEstadoOrdenes();
}
