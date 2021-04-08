package com.willian.msuser.service;

import com.willian.msuser.model.User;
import com.willian.msuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseGet(User::new);
    }

    public User findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseGet(User::new);
    }
}
