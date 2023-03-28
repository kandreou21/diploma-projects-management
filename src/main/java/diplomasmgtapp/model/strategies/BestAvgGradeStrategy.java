package diplomasmgtapp.model.strategies;

import diplomasmgtapp.model.Application;

//@Component
public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm {

	@Override
	protected boolean compareApplication(Application app, Application bestApp) {
		if (app.getApplicantStudent().getAverageGrade() > bestApp.getApplicantStudent().getAverageGrade()) {
			return true;
		}
		return false;
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
