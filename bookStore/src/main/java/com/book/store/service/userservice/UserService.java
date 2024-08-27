package com.book.store.service.userservice;

import com.book.store.dto.userdto.LoginDTO;
import com.book.store.dto.userdto.UserDTO;
import com.book.store.jwtutil.userjwtutil.UserJwtUtil;
import com.book.store.model.usermodel.User;
import com.book.store.repository.userrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements UserServiceI {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserJwtUtil userJwtUtil;

    public void registerUser(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode( userDTO.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    public String loginUser(LoginDTO loginDTO) {
         User  user =  userRepository.findByUsername( loginDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));

        if (passwordEncoder.matches( loginDTO.getPassword(),  user.getPassword())) {
            return  userJwtUtil.generateToken( user.getUsername());
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    public boolean verifyUser(String token){
        String token_username=userJwtUtil.extractUsername(token);
        userRepository.findByUserName
    }

}
