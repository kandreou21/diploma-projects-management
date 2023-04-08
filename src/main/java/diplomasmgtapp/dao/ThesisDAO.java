package diplomasmgtapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomasmgtapp.model.Thesis;

@Repository
public interface ThesisDAO extends JpaRepository<Thesis, Integer> {

	public Thesis findById(int id);

}
