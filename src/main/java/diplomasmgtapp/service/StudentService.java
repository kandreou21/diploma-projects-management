package diplomasmgtapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Student;
import diplomasmgtapp.model.Subject;

@Service
public interface StudentService {
	public void saveProfile(Student student);
	public Student retrieveProfile(String username);
	public List<Subject> listStudentSubjects();
	public List<Application> listApplications(String username);
	public void applyToSubject(String username, int subjectId);
	public void deleteById(int id);
}
