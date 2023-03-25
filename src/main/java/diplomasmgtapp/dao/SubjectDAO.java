package diplomasmgtapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomasmgtapp.model.Subject;

@Repository
public interface SubjectDAO extends JpaRepository<Subject, Integer> {

	public Subject findById(int theId);
	public Subject findByTitle(String title);
	public List<Subject> findAll();
}
