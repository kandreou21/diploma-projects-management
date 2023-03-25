package diplomasmgtapp.model.strategies;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Student;

public class ThresholdStrategy implements BestApplicantStrategy{

	public Student findBestApplicant(List<Application> applications) {
		Scanner input = new Scanner(System.in).useLocale(Locale.US);
		System.out.print("Give the threshold for Average Grade:");
		double gradeThreshold = input.nextDouble();
		System.out.print("Give the threshold for the number of Remaining Courses:");
		int coursesThreshold = input.nextInt();
		
		Student bestApplicant = new Student("dummy");
		for (Application app : applications) {
			if (app.getApplicantStudent().getAverageGrade() > gradeThreshold && app.getApplicantStudent().getRemainingCourses() < coursesThreshold) {
				bestApplicant = app.getApplicantStudent();
			}
		}
		System.out.println("Best Applicant: " + bestApplicant.getFullname());
		return bestApplicant;  
	}

}
