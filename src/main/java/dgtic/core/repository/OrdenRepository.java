package dgtic.core.repository;

import dgtic.core.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrdenRepository extends JpaRepository<Orden,Integer> {
    @Query("select p from Orden p where p.id_orden = ?1")
    public Optional<Orden> findById(Integer id);
}
