package diplomasmgtapp.model.strategies;

import org.springframework.stereotype.Component;

import diplomasmgtapp.model.Application;

@Component("StrategyB")
public class FewestCoursesStrategy extends TemplateStrategyAlgorithm {

	@Override
	protected int compareApplication(Application app, Application bestApp) {
		if (app.getApplicantStudent().getRemainingCourses() < bestApp.getApplicantStudent().getRemainingCourses()) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "FewestCoursesStrategy";
	}
}
