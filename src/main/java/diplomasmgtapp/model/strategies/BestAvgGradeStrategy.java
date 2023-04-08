package diplomasmgtapp.model.strategies;

import org.springframework.stereotype.Component;

import diplomasmgtapp.model.Application;

@Component("StrategyA")
public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm {

	@Override
	protected int compareApplication(Application app, Application bestApp) {
		if (app.getApplicantStudent().getAverageGrade() > bestApp.getApplicantStudent().getAverageGrade()) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "BestAvgGradeStrategy";
	}
}
