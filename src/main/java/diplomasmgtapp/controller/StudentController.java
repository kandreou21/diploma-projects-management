package diplomasmgtapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Student;
import diplomasmgtapp.model.Subject;
import diplomasmgtapp.service.ProfessorService;
import diplomasmgtapp.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
		@Autowired
		private StudentService studentService;
		
		//@Autowired
		//private SubjectService subjectService;
		
		//@Autowired
		//private SubjectService userService;

		@RequestMapping("/dashboard")
		public String getProfessorMainMenu() {
			return "student/dashboard";
		}
		
		@RequestMapping("/setProfileInfo") //after login page lathos auto einai gia add
		public String setProfileInfo(Model model) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName(); //username
			
			Student student = studentService.retrieveProfile(username);
			model.addAttribute("student", student);
			return "student/student-form";
		}
		
		@RequestMapping("/save")
		public String saveProfile(@ModelAttribute("student") Student student, Model model) {
			studentService.saveProfile(student);		
			// use a redirect to prevent duplicate submissions
			return "redirect:/student/dashboard";
		}
		
		@RequestMapping("/retrieveProfile")
		public String retrieveProfile(Model model) {
			return null;
		}
		
		@RequestMapping("/listAvailableSubjects")
		public String listProfessorSubjects(Model model) {
			return null;
		}
		
		@RequestMapping("/showSubjectForm")
		public String showSubjectForm(Model model) {
			return null;
		}
		
		@RequestMapping("/applyToSubject")
		public String listApplications(Integer id, Model model) {
			return null;
		}
}
