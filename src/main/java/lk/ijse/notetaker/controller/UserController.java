package lk.ijse.notetaker.controller;

import lk.ijse.notetaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    // Health Check
    // Save User
    // Get All Users
    // Get User
    // Update User
    // Delete User
}
