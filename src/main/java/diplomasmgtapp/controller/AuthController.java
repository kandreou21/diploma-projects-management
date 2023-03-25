package diplomasmgtapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Role;
import diplomasmgtapp.model.Student;
import diplomasmgtapp.model.User;
import diplomasmgtapp.service.ProfessorService;
import diplomasmgtapp.service.StudentService;
import diplomasmgtapp.service.UserService;

@Controller
public class AuthController {	
    @Autowired
    UserService userService;
    
    @Autowired
    ProfessorService professorService;
    
    @Autowired
    StudentService studentService;

    @RequestMapping("/login") //GENIKA edw sto request mapping mporo na balo /login/{price} kai h methodos na exei os arguemnt p.x. login(@PathVariable double price)
    public String login(){
        return "auth/signin";
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @RequestMapping("/save")
    public String registerUser(@ModelAttribute("user") User user, Model model){
       
        if(userService.isUserPresent(user)){
            model.addAttribute("successMessage", "User already registered!");
            return "auth/signin";
        }

        userService.saveUser(user);
        if (user.getRole() == Role.PROFESSOR) {
        	Professor professor = new Professor(user.getUsername());
        	professorService.saveProfile(professor);
        }
        else if (user.getRole() == Role.STUDENT) {
        	Student student = new Student(user.getUsername());
        	studentService.saveProfile(student);
        }
        model.addAttribute("successMessage", "User registered successfully!");

        return "auth/signin";
    }
    /*
    @RequestMapping("/users") //test, @RestController to work kai allages sto WebSecurityConfig
    public List<User> getUsers(){
    	return userService.getUsers();
    }
    */
}

