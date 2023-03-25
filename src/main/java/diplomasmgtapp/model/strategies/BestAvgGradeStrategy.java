package diplomasmgtapp.model.strategies;

import org.springframework.stereotype.Component;

import diplomasmgtapp.model.Application;

//@Component
public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm {

	@Override
	protected int compareApplication(Application app, Application bestApp) {
		if (app.getApplicantStudent().getAverageGrade() > bestApp.getApplicantStudent().getAverageGrade()) {
			return 1;
		}
		return 0;
	}
	/*
	public Student findBestApplicant(List<DiplomaProjectsManagementApp> applications) {
		DiplomaProjectsManagementApp bestApp = applications.get(0);
		for (DiplomaProjectsManagementApp app : applications) {
			if (app.getApplicantStudent().getAverageGrade() > bestApp.getApplicantStudent().getAverageGrade()) {
				bestApp = app;
			}
		}
		System.out.println("Best Applicant: " + bestApp.getApplicantStudent().getFullname());
		return bestApp.getApplicantStudent();  
	}
	*/
}
