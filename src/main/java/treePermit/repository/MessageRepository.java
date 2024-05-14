package treePermit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import treePermit.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
