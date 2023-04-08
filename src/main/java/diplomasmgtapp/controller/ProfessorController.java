package diplomasmgtapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Subject;
import diplomasmgtapp.model.Thesis;
import diplomasmgtapp.model.strategies.BestApplicantStrategy;
import diplomasmgtapp.model.strategies.ThresholdStrategy;
import diplomasmgtapp.service.ProfessorService;
import diplomasmgtapp.service.SubjectService;
import diplomasmgtapp.service.ThesisService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private ThesisService thesisService;
	
	@RequestMapping("/dashboard")
	public String getProfessorMainMenu() {
		return "professor/dashboard";
	}
	
	@RequestMapping("/setProfileInfo") 
	public String setProfileInfo(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName(); 
		
		Professor professor = professorService.retrieveProfile(username);
		model.addAttribute("professor", professor);
		return "professor/professor-form";
	}

	@RequestMapping("/retrieveProfile")
	public String retrieveProfile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName(); 

		Professor professor = professorService.retrieveProfile(username);
		model.addAttribute("professor", professor);
		return "professor/retrieve-professor";
	}
	
	@RequestMapping("/save")
	public String saveProfile(@ModelAttribute("professor") Professor professor, Model model) {
		professorService.saveProfile(professor);		
		// use a redirect to prevent duplicate submissions
		return "redirect:/professor/dashboard";
	}
	
	@RequestMapping("/listProfessorSubjects")
	public String listProfessorSubjects(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Subject> subjects = professorService.listProfessorSubjects(username);
				
		model.addAttribute("subjects", subjects);
		return "professor/list-subjects";
	}
	
	@RequestMapping("/showSubjectForm")
	public String showSubjectForm(Model model) {
		Subject subject = new Subject();
		
		model.addAttribute("subject", subject);		
		return "professor/subject-form";
	}
	
	@RequestMapping("/addSubject")
	public String addSubject(@ModelAttribute("subject") Subject subject) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		professorService.addSubject(username, subject);		
		// use a redirect to prevent duplicate submissions
		return "redirect:/professor/listProfessorSubjects";
	}
	
	@RequestMapping("/deleteSubject")
	public String delete(@RequestParam("subjectId") int theId) {
		subjectService.deleteById(theId);
		return "redirect:/professor/listProfessorSubjects";	
	}
	
	@RequestMapping("/listApplications")
	public String listApplications(@RequestParam("subjectId") int subjectId, Model model) {
		List<Application> applications = professorService.listApplications(subjectId);
		
		model.addAttribute("applications", applications);
		model.addAttribute("subject", subjectService.findById(subjectId));
		
		List<BestApplicantStrategy> strategies = professorService.getBestApplicantStrategies();
		model.addAttribute("strategies", strategies);
		return "professor/list-applications";
	}
	
	@RequestMapping("/assignExplicitly")
	public String assignSubjectExplicitly(@RequestParam("applicationId") int applicationId, Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		professorService.assignSubjectExplicitly(username, applicationId);
		model.addAttribute("applicationId", applicationId);
		return "redirect:/professor/listProfessorSubjects";
	}
	
	//assign via strategy
	@RequestMapping("/assign")
	public String assignSubject(@RequestParam("subjectId") int subjectId, 
								@RequestParam("strategyIndex") int strategyIndex,
								@RequestParam(value="gradeThreshold", required=false) Double gradeThreshold,
						        @RequestParam(value="coursesThreshold", required=false) Integer coursesThreshold,
								Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		List<BestApplicantStrategy> strategies = professorService.getBestApplicantStrategies();
		Subject subject = subjectService.findById(subjectId);
		
		model.addAttribute("subject", subject);
		model.addAttribute("strategies", strategies);
	
		if (strategies.get(strategyIndex) instanceof ThresholdStrategy) {
			professorService.assignThresholdStrategy(username, subjectId, gradeThreshold, coursesThreshold);
			return "redirect:/professor/listProfessorSubjects";
		}	
		
		professorService.assignSubject(username, subjectId, strategyIndex);
		return "redirect:/professor/listProfessorSubjects";
	}
	
	@RequestMapping("/listProfessorTheses")
	public String listProfessorTheses(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
	
		List<Thesis> theses = professorService.listProfessorTheses(username);
		model.addAttribute("theses", theses);
		return "professor/list-theses";
	}
	
	@RequestMapping("/showThesisForm")
	public String showThesisForm(@RequestParam("thesisId") int thesisId, Model model) {
		Thesis thesis = thesisService.findById(thesisId);
		
		model.addAttribute("thesis", thesis);
		return "professor/thesis-form";
	}
	
	@RequestMapping("/saveThesis")
	public String saveThesis(@RequestParam("thesisId") int thesisId, @ModelAttribute("thesis") Thesis thesis) {
		thesisService.updateThesis(thesisId, thesis);
		return "redirect:/professor/listProfessorTheses"; 
	}
	
	@RequestMapping("/showGrades")
	public String showGrades(@RequestParam("thesisId") int thesisId, Model model) {
		Thesis thesis = thesisService.findById(thesisId);
		model.addAttribute("thesis", thesis);
		return "professor/show-grades";
	}
}
