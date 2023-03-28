package diplomasmgtapp.model.strategies;

import org.springframework.stereotype.Component;

import diplomasmgtapp.exceptions.WrongStrategyException;

//@Component needed if i hadnt initialised strategies in constructor of SubjectServiceImpl
public class BestApplicantStrategyFactory {

	public static BestApplicantStrategy createStrategy(String strategy) 
		throws WrongStrategyException {

		switch (strategy) {
			case "Best Avg Grade":
				return new BestAvgGradeStrategy();
			case "Fewest Courses":
				return new FewestCoursesStrategy();
			case "Threshold":
				return new ThresholdStrategy();
			case "Random":
				return new RandomChoiceStrategy();
			case "Explicit":
				return new ExplicitAssignmentStrategy();
			default:
				throw new WrongStrategyException();
		}
	}
}
