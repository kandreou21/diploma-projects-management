package diplomasmgtapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomasmgtapp.model.Professor;

@Repository
public interface ProfessorDAO extends JpaRepository<Professor, Integer> {

	public Professor findByUsername(String username);
}
