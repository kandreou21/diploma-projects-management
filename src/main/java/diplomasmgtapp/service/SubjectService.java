package diplomasmgtapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import diplomasmgtapp.model.Subject;

@Service
public interface SubjectService {
	public List<Subject> findAll();
	public Subject findById(int theId);
	public void deleteById(int theId);
	public void save(Subject theSubject);
}
