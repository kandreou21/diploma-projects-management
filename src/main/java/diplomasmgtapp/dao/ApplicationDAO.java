package diplomasmgtapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Student;

@Repository
public interface ApplicationDAO extends JpaRepository<Application, Integer> {

	public Application findById(int id);
	public void deleteById(int id);
	public List<Application> findAllByApplicantStudent(Student student);
}
