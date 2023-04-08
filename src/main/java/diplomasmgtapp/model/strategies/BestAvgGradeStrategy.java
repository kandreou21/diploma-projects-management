package diplomasmgtapp.model.strategies;

import diplomasmgtapp.model.Application;

@Component("StrategyA")
public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm {

	@Override
	protected boolean compareApplication(Application app, Application bestApp) {
		if (app.getApplicantStudent().getAverageGrade() > bestApp.getApplicantStudent().getAverageGrade()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "BestAvgGradeStrategy";
	}
}
