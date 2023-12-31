package com.questapp.service;

import com.questapp.dto.request.UserCreateRequest;
import com.questapp.model.User;
import com.questapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    public void saveUser(UserCreateRequest userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
    }

    public void updateUser(Long id, User user) {
        User dbUser = getUser(id);
        dbUser.setUsername(user.getUsername());
        dbUser.setPassword(user.getPassword());
        userRepository.save(dbUser);
    }

    public void deleteUserById(Long id) {
        User user = getUser(id);
        userRepository.delete(user);
    }
}
