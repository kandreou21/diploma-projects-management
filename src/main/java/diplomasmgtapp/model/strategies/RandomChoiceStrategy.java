package diplomasmgtapp.model.strategies;

import java.util.List;
import java.util.Random;

import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Student;

public class RandomChoiceStrategy implements BestApplicantStrategy{
	
	public Student findBestApplicant(List<Application> applications) {
		Application randomApp = applications.get(new Random().nextInt(applications.size()));
		System.out.println(randomApp.toString());
		return randomApp.getApplicantStudent();  
	}
}

