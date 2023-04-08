package diplomasmgtapp.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Student;
import diplomasmgtapp.service.ProfessorService;
import diplomasmgtapp.service.StudentService;

@Configuration
public class CustomSecuritySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired 
	ProfessorService professorService;
	
	@Autowired 
	StudentService studentService;
	
	@Override
    protected void handle(
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		Authentication authentication)
    throws java.io.IOException {
        String targetUrl = determineTargetUrl(authentication);
        if(response.isCommitted()) return;
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication){
        String url = "/login?error=true";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        
        for(GrantedAuthority a : authorities){
            roles.add(a.getAuthority());
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(roles.contains("PROFESSOR")){
        	Professor professor = professorService.retrieveProfile(username);
        	if (professor.getFullname() == null) { 
        		url = "/professor/setProfileInfo";  //at the first time login set profile info     		
        	} else {
        		url = "/professor/dashboard";  
        	}
        }else if(roles.contains("STUDENT")) {
        	Student student = studentService.retrieveProfile(username);
        	if (student.getFullname() == null) {
        		url = "/student/setProfileInfo";  		
        	} else {
        		url = "/student/dashboard";  
        	}
        }
        return url;
    }
}
