package diplomasmgtapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Subject;
import diplomasmgtapp.model.Thesis;

@Service
public interface ProfessorService {
	//ta onomata ton arguments einai ektimiseis
	public Professor retrieveProfile(String username);
	public void saveProfile(Professor professor);
	public List<Subject> listProfessorSubjects(String professorName);
	public void addSubject(String professorName, Subject subject);
	public void deleteSubject(String professorName, Subject subject);
	public List<Application> listApplications(String professorName, int id);
	public List<Thesis> listProfessorTheses(String professorName);
	public void assignSubject(String professorName, int id);
	public void setImplementationGrade(Thesis thesis, double implementationGrade, String professorName);
	public void setReportGrade(Thesis thesis, double reportGrade, String professorName);
	public void setPresentationGrade(Thesis thesis, double presentationGrade, String professorName);
	public double calculateThesisGrade(Thesis thesis, String professorName);
	public Professor findByFullname(String professorName);
}

