package diplomasmgtapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Subject;
import diplomasmgtapp.model.User;
import diplomasmgtapp.service.ProfessorService;
import diplomasmgtapp.service.SubjectService;
import diplomasmgtapp.service.UserService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private UserService userService;

	@RequestMapping("/dashboard")
	public String getProfessorMainMenu() {
		return "professor/dashboard";
	}
	
	@RequestMapping("/setProfileInfo") //after login page lathos auto einai gia add
	public String setProfileInfo(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName(); //username
		
		Professor professor = professorService.retrieveProfile(username);
		model.addAttribute("professor", professor);
		return "professor/professor-form";
	}

	@RequestMapping("/retrieveProfile")
	public String retrieveProfile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName(); //username

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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName(); //username

		List<Subject> subjects = professorService.listProfessorSubjects(username);
		
		// add to the spring model
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
	public String addSubject(@ModelAttribute("subject") Subject subject, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName(); //username

		subject.setSupervisor(professorService.retrieveProfile(username));
		subjectService.save(subject);
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
	public String listApplications(Integer id, Model model) {
		return null;
	}
	
	@RequestMapping("/assign")
	public String assignSubject(Integer id, Model model) {
		return null;
	}
	
	@RequestMapping("/listProfessorTheses")
	public String listProfessorTheses(Model model) {
		return null;
	}
}
