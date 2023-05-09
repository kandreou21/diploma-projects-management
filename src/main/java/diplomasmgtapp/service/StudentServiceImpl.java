package diplomasmgtapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomasmgtapp.dao.ApplicationDAO;
import diplomasmgtapp.dao.StudentDAO;
import diplomasmgtapp.dao.SubjectDAO;
import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Student;
import diplomasmgtapp.model.Subject;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private SubjectDAO subjectDAO;
	
	@Autowired
	private ApplicationDAO applicationDAO;
	
 	@Override
	public void saveProfile(Student student) {
		studentDAO.save(student);
	}

	@Override
	public Student retrieveProfile(String username) {
		return studentDAO.findByUsername(username);
	}

	@Override
	public List<Subject> listStudentSubjects() {
		return subjectDAO.findAll();
	}

	@Override
	public void applyToSubject(String username, int subjectId) {
		Student student = studentDAO.findByUsername(username);
		Subject subject = subjectDAO.findById(subjectId);
		Application application = new Application(student, subject);
		student.applyToDiplomaThesis(application);
		studentDAO.save(student);
	}

	@Override
	public List<Application> listApplications(String username) {
		return applicationDAO.findAllByApplicantStudent(retrieveProfile(username));
	}

	@Override
	public void deleteById(int id){
		studentDAO.deleteById(id);
	}
}