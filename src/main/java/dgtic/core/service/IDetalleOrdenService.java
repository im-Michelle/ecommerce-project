package dgtic.core.service;

import dgtic.core.entity.Cliente;
import dgtic.core.model.DetalleOrden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDetalleOrdenService {
    public Page<DetalleOrden> findAll(Pageable pageable);
    public void guardar(DetalleOrden detalleOrden);
    public void borrar(Integer id);
    public DetalleOrden buscarDetalleOrden(Integer id);
    List<DetalleOrden> buscarDetalleOrdenes();
}
