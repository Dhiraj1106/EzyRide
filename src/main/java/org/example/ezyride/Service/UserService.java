package org.example.ezyride.Service;

import org.example.ezyride.DAO.UserDAO;
import org.example.ezyride.Entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User registerUser(User user) {

        if (userDAO.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        return userDAO.save(user);
    }
}