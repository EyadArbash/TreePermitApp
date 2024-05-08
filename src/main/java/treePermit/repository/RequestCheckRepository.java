package treePermit.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import treePermit.model.RequestCheck;

public interface RequestCheckRepository extends JpaRepository<RequestCheck, Long> {
}
