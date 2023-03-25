package diplomasmgtapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import diplomasmgtapp.model.Subject;
import diplomasmgtapp.model.strategies.BestApplicantStrategy;
import diplomasmgtapp.dao.SubjectDAO;

@Service
public class SubjectServiceImpl implements SubjectService {

	private List<BestApplicantStrategy> bestApplicantStrategies; //guidelines
	
	private SubjectDAO subjectDAO;
	
	public SubjectServiceImpl() {
		super();
	}

	@Autowired
	public SubjectServiceImpl(List<BestApplicantStrategy> bestApplicantStrategies, SubjectDAO theSubjectRepository) {
		this.bestApplicantStrategies = bestApplicantStrategies;
		this.subjectDAO = theSubjectRepository;
	}
	
	@Override
	@Transactional
	public List<Subject> findAll() {
		return subjectDAO.findAll();
	}

	@Override
	@Transactional
	public Subject findById(int theId) {
		Subject result = subjectDAO.findById(theId);
				
		if (result != null ) {
			return result;
		}
		else {
			throw new RuntimeException("Did not find subject id - " + theId);
		}
	}

	@Override
	@Transactional
	public void save(Subject theSubject) {
		subjectDAO.save(theSubject);
	}

	@Override
	public void deleteById(int theId) {
		subjectDAO.deleteById(theId);
	}
}
