package com.book.store.controller.usercontroller;


import com.book.store.dto.admindto.AdminDto;
import com.book.store.dto.admindto.LoginDto;
import com.book.store.dto.userdto.LoginDTO;
import com.book.store.dto.userdto.UserDTO;
import com.book.store.model.usermodel.User;
import com.book.store.service.adminservice.AdminService;
import com.book.store.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin
@RequestMapping("/bookstore_user")

public class UserController {
    @Autowired
    private UserService  userService;

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody User userDTO){
         userService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User account created successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO  loginDTO){
        String token =  userService.loginUser(loginDTO);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PostMapping("/verification/{token}")
    public ResponseEntity<?> userVerification(@PathVariable String token) {
        boolean isVerified = userService.verifyToken(token);
        if (isVerified) {
            return ResponseEntity.ok("User account verified successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
        }
    }
}
