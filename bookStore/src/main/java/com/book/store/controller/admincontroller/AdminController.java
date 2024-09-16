package com.book.store.controller.admincontroller;

import com.book.store.dto.admindto.AdminDto;
import com.book.store.dto.admindto.LoginDto;
import com.book.store.service.adminservice.AdminService;
import jakarta.validation.Valid;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/bookstore_user/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/registration")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody AdminDto adminDto){
        adminService.registerAdmin(adminDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin account created successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginDto loginDto){
        String token = adminService.loginAdmin(loginDto);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
