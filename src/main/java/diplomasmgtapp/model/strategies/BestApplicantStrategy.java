package diplomasmgtapp.model.strategies;

import java.util.List;

import org.springframework.stereotype.Component;

import diplomasmgtapp.model.Application;

@Component
public interface BestApplicantStrategy {

	public Application findBestApplicant(List<Application> applications);
}
