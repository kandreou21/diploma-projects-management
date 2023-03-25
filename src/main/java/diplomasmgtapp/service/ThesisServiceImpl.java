package diplomasmgtapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import diplomasmgtapp.dao.ThesisDAO;
import diplomasmgtapp.model.Thesis;

public class ThesisServiceImpl implements ThesisService {

	@Autowired
	private ThesisDAO thesisDAO;

	@Override
	public void setImplementationGrade(double implementationGrade, int id) {
		Thesis thesis = thesisDAO.findById(id);
		thesis.setImplementationGrade(implementationGrade);
		thesisDAO.save(thesis);
	}

	@Override
	public void setReportGrade(double reportGrade, int id) {
		Thesis thesis = thesisDAO.findById(id);
		thesis.setReportGrade(reportGrade);
		thesisDAO.save(thesis);
	}

	@Override
	public void setPresentationGrade(double presentationGrade, int id) {
		Thesis thesis = thesisDAO.findById(id);
		thesis.setPresentationGrade(presentationGrade);
		thesisDAO.save(thesis);
	}

	@Override
	public double calculateThesisGrade(int id) {
		return thesisDAO.findById(id).getOverallGrade();
	}
}
