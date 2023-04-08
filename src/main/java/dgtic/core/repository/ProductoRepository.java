package dgtic.core.repository;

import dgtic.core.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
    @Query("select p from Producto p where p.nombre like %?1%")
    public List<Producto> findByNombre(String dato);
}
