package diplomasmgtapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diplomasmgtapp.dao.ApplicationDAO;
import diplomasmgtapp.dao.ProfessorDAO;
import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Subject;
import diplomasmgtapp.model.Thesis;
import diplomasmgtapp.model.strategies.BestApplicantStrategy;
import diplomasmgtapp.model.strategies.ThresholdStrategy;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	@Autowired
	private List<BestApplicantStrategy> bestApplicantStrategies; 
	private BestApplicantStrategy strategy;	
	
	@Autowired
	private ProfessorDAO professorDAO;
	
	@Autowired
	private ApplicationDAO applicationDAO;
	
	@Autowired
	private SubjectService subjectService;
	
	@Override
	public List<BestApplicantStrategy> getBestApplicantStrategies() {
		return bestApplicantStrategies;
	}

	public void setBestApplicantStrategies(List<BestApplicantStrategy> bestApplicantStrategies) {
		this.bestApplicantStrategies = bestApplicantStrategies;
	}

	public void configureStrategy(BestApplicantStrategy strategy) {
		this.strategy = strategy;
	}

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
	public void addSubject(String username, Subject subject) {
		Professor professor = professorDAO.findByUsername(username);
		professor.addSubject(subject);
		professorDAO.save(professor);
	}

	@Override
	public List<Application> listApplications(int subjectId) {
		return subjectService.findById(subjectId).getApplications();
	}

	@Override
	public List<Thesis> listProfessorTheses(String username) {
		return professorDAO.findByUsername(username).getTheses();
	}
	
	@Override
	public void assignSubjectExplicitly(String username, int applicationId) {
		Professor professor = professorDAO.findByUsername(username);
		Application application = applicationDAO.findById(applicationId);
		professor.assignThesis(new Thesis(application.getApplicantStudent(), application.getSubject()));
		applicationDAO.deleteById(applicationId);
		professorDAO.save(professor);
	}
	
	@Override
	public void assignThresholdStrategy(String username, int subjectId, double gradeThreshold, int coursesThreshold) {
		ThresholdStrategy strategy = new ThresholdStrategy();
		strategy.setGradeThreshold(gradeThreshold);
		strategy.setCoursesThreshold(coursesThreshold);
		executeAssignment(username, subjectId, strategy);
	}

	@Override
	public void assignSubject(String username, int subjectId, int strategyIndex) {
		configureStrategy(bestApplicantStrategies.get(strategyIndex));
		executeAssignment(username, subjectId, strategy);
	}
	
	private void executeAssignment(String username, int subjectId, BestApplicantStrategy strategy) { //template method
		Professor professor = professorDAO.findByUsername(username);
		Subject subject = subjectService.findById(subjectId);
		List<Application> applications = subject.getApplications();
		Application bestApp = null;
		try {	
			bestApp = strategy.findBestApplicant(applications);
		} catch(NullPointerException e){}
		if (bestApp != null) {
			professor.assignThesis(new Thesis(bestApp.getApplicantStudent(), bestApp.getSubject()));
			subject.removeApplication(bestApp);
			applicationDAO.deleteById(bestApp.getId());
			professorDAO.save(professor);
		}
	}
}
