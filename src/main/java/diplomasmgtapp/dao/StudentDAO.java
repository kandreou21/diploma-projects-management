package diplomasmgtapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomasmgtapp.model.Student;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
	
	public Student findById(int theId);
	public List<Student> findAll();
	public Student findByFullname(String fullname);
	public Student findByUsername(String username);
}
