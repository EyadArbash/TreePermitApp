package treePermit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import treePermit.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByReceiver(String username);
	
}
