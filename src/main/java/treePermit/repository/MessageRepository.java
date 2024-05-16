package treePermit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import treePermit.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByReceiver(String receiver);

	List<Message> findBySenderOrReceiver(String sender, String receiver);
}
