package diplomasmgtapp.model.strategies;

import diplomasmgtapp.model.Application;
import org.springframework.stereotype.Component;

@Component("StrategyA")
public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm {

	@Override
	protected boolean compareApplication(Application app, Application bestApp) {
		return app.getApplicantStudent().getAverageGrade() > bestApp.getApplicantStudent().getAverageGrade();
	}

	@Override
	public String toString() {
		return "BestAvgGradeStrategy";
	}
}
