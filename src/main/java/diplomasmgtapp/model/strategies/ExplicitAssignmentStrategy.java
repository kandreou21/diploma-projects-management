package diplomasmgtapp.model.strategies;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Student;

//@Component
public class ExplicitAssignmentStrategy implements BestApplicantStrategy {

	@Override
	public Student findBestApplicant(List<Application> applications) {
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < applications.size(); i++) {
			System.out.println(i + ":" + applications.get(i).getApplicantStudent().getFullname());
		}
		System.out.print("Select the wanted applicant by entering their number: ");
		int id = input.nextInt();
		System.out.println(applications.get(id).getApplicantStudent().getFullname());
		return applications.get(id).getApplicantStudent();
	}

}
