package diplomasmgtapp.model;

import java.util.ArrayList;
import java.util.List;

import diplomasmgtapp.exceptions.WrongStrategyException;
import diplomasmgtapp.model.strategies.*;

public class Main {
	//testing diplomasmgtapp.model classes
	public static void main(String[] args) throws WrongStrategyException {
		List<Application> apps = new ArrayList<Application>();
		Student s1 = new Student("kostas");
		Student s2 = new Student("giorgos");
		Subject sub1 = new Subject();
		Subject sub2 = new Subject();
		Application app1 = new Application(s1, sub1);
		Application app2 = new Application(s2, sub2);
		apps.add(app1);
		apps.add(app2);
		BestApplicantStrategy strategy = new BestAvgGradeStrategy();
		//BestApplicantStrategy strategy = new FewestCoursesStrategy();
		//BestApplicantStrategy strategy = new ThresholdStrategy();
		//BestApplicantStrategy strategy = new RandomChoiceStrategy();
		BestApplicantStrategy strategy1 = BestApplicantStrategyFactory.createStrategy("Best Avg Grade");
		//BestApplicantStrategy strategy1 = BestApplicantStrategyFactory.createStrategy("Fewest Courses");
		//BestApplicantStrategy strategy1 = BestApplicantStrategyFactory.createStrategy("Threshold");
		//BestApplicantStrategy strategy1 = BestApplicantStrategyFactory.createStrategy("Random");
		/*
		try {
			BestApplicantStrategy strategy1 = BestApplicantStrategyFactory.createStrategy("throw");
			strategy1.findBestApplicant(apps);
		} catch(WrongStrategyException e){
			System.out.println("Wrong Strategy tried to be created");
		}
		*/
		
		strategy.findBestApplicant(apps);
		strategy1.findBestApplicant(apps);
	}
}
