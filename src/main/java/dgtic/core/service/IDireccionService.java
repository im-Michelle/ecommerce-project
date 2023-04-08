package dgtic.core.service;

import dgtic.core.entity.Cliente;
import dgtic.core.entity.Direccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IDireccionService {
    public Page<Direccion> findAll(Pageable pageable);
    public void guardar(Direccion direccion);
    public void borrar(Integer id);
    public Direccion buscarDireccion(Integer id);
    List<Direccion> buscarDirecciones();
}
