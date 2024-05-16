package treePermit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import treePermit.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	User findByUsername(String username);
}
