package diplomasmgtapp.model.strategies;

import java.util.List;

import org.springframework.stereotype.Component;

import diplomasmgtapp.model.Application;

@Component("StrategyD")
public class ThresholdStrategy implements BestApplicantStrategy{

	private double gradeThreshold;
	private double coursesThreshold;
	
	public Application findBestApplicant(List<Application> applications) {
		Application bestApp = null;
		for (Application app : applications) {
			if (app.getApplicantStudent().getAverageGrade() >= gradeThreshold && app.getApplicantStudent().getRemainingCourses() <= coursesThreshold) {
				bestApp = app;
			}
		}
		System.out.println("Best Applicant: " + bestApp.getApplicantStudent().getFullname());
		return bestApp;  
	}

	public double getGradeThreshold() {
		return gradeThreshold;
	}

	public void setGradeThreshold(double gradeThreshold) {
		this.gradeThreshold = gradeThreshold;
	}

	public double getCoursesThreshold() {
		return coursesThreshold;
	}

	public void setCoursesThreshold(double coursesThreshold) {
		this.coursesThreshold = coursesThreshold;
	}

	@Override
	public String toString() {
		return "ThresholdStrategy";
	}
}
