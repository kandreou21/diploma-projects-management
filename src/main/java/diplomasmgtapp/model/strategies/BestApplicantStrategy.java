package diplomasmgtapp.model.strategies;

import java.util.List;

import diplomasmgtapp.model.Student;
import diplomasmgtapp.model.Application;

public interface BestApplicantStrategy {

	public Student findBestApplicant(List<Application> applications);
}
