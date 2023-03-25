package diplomasmgtapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diplomasmgtapp.dao.ProfessorDAO;
import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Subject;
import diplomasmgtapp.model.Thesis;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	@Autowired
	private ProfessorDAO professorDAO;
	
	@Override
	public Professor retrieveProfile(String username) {
		return professorDAO.findByUsername(username);
	}

	@Override
	@Transactional
	public void saveProfile(Professor professor) {
		professorDAO.save(professor);
	}

	@Override
	public List<Subject> listProfessorSubjects(String username) {
		return professorDAO.findByUsername(username).getSubjects();
	}

	@Override
	public void addSubject(String professorName, Subject subject) {
		Professor professor = professorDAO.findByUsername(professorName);
		professor.addSubject(subject);
		professorDAO.save(professor);
	}

	@Override
	public void deleteSubject(String professorName, Subject subject) {
		Professor professor = professorDAO.findByFullname(professorName);
		professor.removeSubject(subject);
		professorDAO.save(professor);
	}
	
	@Override
	public List<Application> listApplications(String professorName, int subjectId) {
		return professorDAO.findByFullname(professorName).getSubjects().get(subjectId).getApplications();
	}

	@Override
	public List<Thesis> listProfessorTheses(String professorName) {
		return professorDAO.findByFullname(professorName).getTheses();
	}
	//not sure to be fixed
	@Override
	public void assignSubject(String professorName, int id) {
		professorDAO.findByFullname(professorName);
	}

	@Override
	public void setImplementationGrade(Thesis thesis, double implementationGrade, String professorName) {
		Professor professor = professorDAO.findByFullname(professorName);
		professor.setImplementationGrade(thesis, implementationGrade);
		professorDAO.save(professor);
	}

	@Override
	public void setReportGrade(Thesis thesis, double reportGrade, String professorName) {
		Professor professor = professorDAO.findByFullname(professorName);
		professor.setImplementationGrade(thesis, reportGrade);
		professorDAO.save(professor);
	}

	@Override
	public void setPresentationGrade(Thesis thesis, double presentationGrade, String professorName) {
		Professor professor = professorDAO.findByFullname(professorName);
		professor.setImplementationGrade(thesis, presentationGrade);
		professorDAO.save(professor);
	}

	@Override
	public double calculateThesisGrade(Thesis thesis, String professorName) {
		Professor professor = professorDAO.findByFullname(professorName);
		return professor.calculateThesisGrade(thesis);
	}

	@Override
	public Professor findByFullname(String professorName) {
		return professorDAO.findByFullname(professorName);
	}
}
