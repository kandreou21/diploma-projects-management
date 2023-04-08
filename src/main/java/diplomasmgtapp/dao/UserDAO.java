package diplomasmgtapp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomasmgtapp.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, String> {  
	
	Optional<User> findByUsername(String username);
	public User findById(int theId);
}

