package diplomasmgtapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Subject;
import diplomasmgtapp.model.Thesis;
import diplomasmgtapp.model.strategies.BestApplicantStrategy;

@Service
public interface ProfessorService {
	public Professor retrieveProfile(String username);
	public void saveProfile(Professor professor);
	public List<Subject> listProfessorSubjects(String username);
	public void addSubject(String username, Subject subject);
	public List<Application> listApplications(int subjectId);
	public List<Thesis> listProfessorTheses(String username);
	public void assignSubjectExplicitly(String username, int applicationId);
	public void assignSubject(String username, int subjectId, int strategyIndex);
	public List<BestApplicantStrategy> getBestApplicantStrategies();
	public void assignThresholdStrategy(String username, int subjectId, double gradeThreshold, int coursesThreshold);
}

