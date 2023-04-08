package diplomasmgtapp.service;

import org.springframework.stereotype.Service;

import diplomasmgtapp.model.User;

@Service
public interface UserService {
	public void saveUser(User user);
    public boolean isUserPresent(User user);
    public User findById(int id);
}