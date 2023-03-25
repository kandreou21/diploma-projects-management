package diplomasmgtapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import diplomasmgtapp.model.User;

@Service
public interface UserService extends UserDetailsService{
	public void saveUser(User user);
    public boolean isUserPresent(User user);
    public User findById(int id);
}