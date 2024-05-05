package treePermit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import treePermit.controller.Antrag;

// Das AntragRepository erweitert JpaRepository, das generische CRUD-Operationen bereitstellt.
public interface AntragRepository extends JpaRepository<Antrag, Long> {
    List<Antrag> findByStadt(String stadt);
}
