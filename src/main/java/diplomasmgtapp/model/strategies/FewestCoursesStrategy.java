package diplomasmgtapp.model.strategies;

import diplomasmgtapp.model.Application;

//@Component
public class FewestCoursesStrategy extends TemplateStrategyAlgorithm {

	@Override
	protected boolean compareApplication(Application app, Application bestApp) {
		if (app.getApplicantStudent().getRemainingCourses() < bestApp.getApplicantStudent().getRemainingCourses()) {
			return true;
		}
		return false;
	}

	/*
	public Student findBestApplicant(List<DiplomaProjectsManagementApp> applications) {
		DiplomaProjectsManagementApp bestApp = applications.get(0);
		for (DiplomaProjectsManagementApp app : applications) {
			if (app.getApplicantStudent().getRemainingCourses() < bestApp.getApplicantStudent().getRemainingCourses()) {
				bestApp = app;
			}
		}
		System.out.println("Best Applicant: " + bestApp.getApplicantStudent().getFullname());
		return bestApp.getApplicantStudent();  
	}
	*/
}
