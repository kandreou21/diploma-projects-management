package diplomasmgtapp.model.strategies;

import org.springframework.stereotype.Component;

import diplomasmgtapp.exceptions.WrongStrategyException;

@Component
public class BestApplicantStrategyFactory {

	public static BestApplicantStrategy createStrategy(String strategy) 
		throws WrongStrategyException {
		
		if (strategy == "Best Avg Grade") {
			return new BestAvgGradeStrategy();
		} else if (strategy == "Fewest Courses") {
			return new FewestCoursesStrategy();
		} else if (strategy == "Threshold") {
			return new ThresholdStrategy();
		} else if (strategy == "Random") {
			return new RandomChoiceStrategy();
		} else throw new WrongStrategyException();
	}
}
