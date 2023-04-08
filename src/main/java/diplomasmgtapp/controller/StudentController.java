package diplomasmgtapp.controller;

import org.springframework.stereotype.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Student;
import diplomasmgtapp.model.Subject;
import diplomasmgtapp.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
		@Autowired
		private StudentService studentService;

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

		@RequestMapping("/listAvailableSubjects")
		public String listProfessorSubjects(Model model) {
			
			List<Subject> allSubjects = studentService.listStudentSubjects();
			model.addAttribute("allSubjects", allSubjects);
			return "student/list-subjects";
		}
		
		@RequestMapping("/applyToSubject")
		public String applyToSubject(@RequestParam("subjectId") int subjectId) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			
			studentService.applyToSubject(username, subjectId);

			return "redirect:/student/listAvailableSubjects";
		}
		
		@RequestMapping("/showApplications")
		public String listApplications(Model model) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			
			List<Application> applications = studentService.listApplications(username);
			model.addAttribute("applications", applications);
			return "student/list-applications";
		}
}
