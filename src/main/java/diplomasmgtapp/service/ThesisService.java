package diplomasmgtapp.service;

import org.springframework.stereotype.Service;

import diplomasmgtapp.model.Thesis;

@Service
public interface ThesisService {

	public Thesis findById(int thesisId);
	public void save(Thesis thesis); 
	public void updateThesis(int thesisId, Thesis thesis);
}
