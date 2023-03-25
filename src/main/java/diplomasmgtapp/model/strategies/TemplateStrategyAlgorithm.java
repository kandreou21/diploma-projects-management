package diplomasmgtapp.model.strategies;

import java.util.List;

import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Student;

public abstract class TemplateStrategyAlgorithm implements BestApplicantStrategy {

	public Student findBestApplicant(List<Application> applications) {
		Application bestApp = applications.get(0);
		for (Application app : applications) {
			if (compareApplication(app, bestApp) == 1)
				bestApp = app;
		}
		System.out.println("Best Applicant: " + bestApp.getApplicantStudent().getFullname());
		return bestApp.getApplicantStudent();  
	}

	protected abstract int compareApplication(Application app, Application bestApp);//template method
}
