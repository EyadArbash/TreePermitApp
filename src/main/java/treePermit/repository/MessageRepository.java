package treePermit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import treePermit.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByReceiver(String receiver);

    List<Message> findBySenderOrReceiver(String sender, String receiver);

    @Query("SELECT m FROM Message m WHERE (m.sender = :sender1 AND m.receiver = :receiver1) OR (m.receiver = :sender1 AND m.sender = :receiver1)")
    List<Message> findMessagesBetween(@Param("sender1") String sender1, @Param("receiver1") String receiver1);
}
