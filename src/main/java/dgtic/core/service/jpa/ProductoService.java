package dgtic.core.service.jpa;

import dgtic.core.entity.Orden;
import dgtic.core.entity.Producto;
import dgtic.core.repository.ProductoRepository;
import dgtic.core.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Page<Producto> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void borrar(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto buscarProducto(Integer id) {
        Optional<Producto> op = productoRepository.findById(id);
        return op.get();
    }

    @Override
    public List<Producto> buscarProductos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarProductos(String dato) {
        return productoRepository.findByNombre(dato);
    }
}
