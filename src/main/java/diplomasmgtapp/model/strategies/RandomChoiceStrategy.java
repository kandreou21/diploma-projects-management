package diplomasmgtapp.model.strategies;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import diplomasmgtapp.model.Application;

@Component("StrategyC")
public class RandomChoiceStrategy implements BestApplicantStrategy{
	
	public Application findBestApplicant(List<Application> applications) {
		Application randomApp = applications.get(new Random().nextInt(applications.size()));	//0-applications.size()-1 tick
		System.out.println(randomApp.getApplicantStudent().getFullname());
		return randomApp;  
	}

	@Override
	public String toString() {
		return "RandomChoiceStrategy";
	}
	
}


