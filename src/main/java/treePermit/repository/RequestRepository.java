package treePermit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import treePermit.model.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByFamilienname(String familienname);
}

