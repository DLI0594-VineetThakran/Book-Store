package com.book.store.service.adminservice;

import com.book.store.dto.admindto.AdminDto;
import com.book.store.dto.admindto.LoginDto;
import com.book.store.jwtutil.adminjwtutil.AdminJwtUtil;
import com.book.store.model.adminmodel.Admin;
import com.book.store.repository.adminrepository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AdminService implements AdminServiceImplementation{
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminJwtUtil adminJwtUtil;

    public void registerAdmin(AdminDto adminDto){
        Admin admin = new Admin();
        admin.setUsername(adminDto.getUserName());
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(new BCryptPasswordEncoder().encode(adminDto.getPassword()));
        admin.setCreatedAt(LocalDateTime.now());

        adminRepository.save(admin);
    }

    public String loginAdmin(LoginDto loginDto) {
        Admin admin = adminRepository.findByUsername(loginDto.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));

        if (passwordEncoder.matches(loginDto.getPassword(), admin.getPassword())) {
            return adminJwtUtil.generateToken(admin.getUsername());
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

}
