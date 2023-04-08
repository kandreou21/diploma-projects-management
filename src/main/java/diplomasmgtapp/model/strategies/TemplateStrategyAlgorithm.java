package diplomasmgtapp.model.strategies;

import java.util.List;

import org.springframework.stereotype.Component;

import diplomasmgtapp.model.Application;

@Component
public abstract class TemplateStrategyAlgorithm implements BestApplicantStrategy {

	public Application findBestApplicant(List<Application> applications) {
		Application bestApp = applications.get(0);
		for (Application app : applications) {
			if (compareApplication(app, bestApp))
				bestApp = app;
		}
		System.out.println("Best Applicant: " + bestApp.getApplicantStudent().getFullname());
		return bestApp;  
	}

	protected abstract boolean compareApplication(Application app, Application bestApp);//template method
}
