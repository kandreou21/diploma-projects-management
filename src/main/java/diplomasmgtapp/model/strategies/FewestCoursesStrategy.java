package diplomasmgtapp.model.strategies;

import org.springframework.stereotype.Component;

import diplomasmgtapp.model.Application;

@Component("StrategyB")
public class FewestCoursesStrategy extends TemplateStrategyAlgorithm {

	@Override
	protected boolean compareApplication(Application app, Application bestApp) {
		if (app.getApplicantStudent().getRemainingCourses() < bestApp.getApplicantStudent().getRemainingCourses()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "FewestCoursesStrategy";
	}
}
