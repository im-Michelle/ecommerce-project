package dgtic.core.service;

import dgtic.core.entity.Orden;
import dgtic.core.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductoService {
    public Page<Producto> findAll(Pageable pageable);
    public void guardar(Producto producto);
    public void borrar(Integer id);
    public Producto buscarProducto(Integer id);
    List<Producto> buscarProductos();
    public List<Producto> buscarProductos(String dato);

}
