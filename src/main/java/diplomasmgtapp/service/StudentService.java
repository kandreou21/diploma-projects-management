package diplomasmgtapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Student;
import diplomasmgtapp.model.Subject;

@Service
public interface StudentService {
	//ta onomata ton arguments einai ektimiseis
	public void saveProfile(Student student);
	public Student retrieveProfile(String username);
	public List<Subject> listStudentSubjects();
	//public void applyToSubject(Application application);
	public void applyToSubject(String subjectTitle, int id); //AUTH EINAI H METHODOS STA REQUIR
}
