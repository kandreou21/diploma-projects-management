package diplomasmgtapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomasmgtapp.dao.ThesisDAO;
import diplomasmgtapp.model.Thesis;

@Service
public class ThesisServiceImpl implements ThesisService {

	@Autowired
	private ThesisDAO thesisDAO;

	@Override
	public Thesis findById(int thesisId) {
		return thesisDAO.findById(thesisId);
	}
	
	@Override
	public void save(Thesis thesis) {
		thesisDAO.save(thesis);
	}
	
	@Override
	public void updateThesis(int thesisId, Thesis thesis) {
		Thesis oldThesis = thesisDAO.findById(thesisId);
		thesis.setStudent(oldThesis.getStudent());
		thesis.setSubject(oldThesis.getSubject());
		
		thesisDAO.save(thesis);	
	}

}
