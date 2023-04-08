package dgtic.core.service;

import dgtic.core.entity.Estado;
import dgtic.core.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEstadoService {
    public Page<Estado> findAll(Pageable pageable);
    public void guardar(Estado estado);
    public void borrar(Integer id);
    public Estado buscarEstado(Integer id);
    List<Estado> buscarEstados();
}
