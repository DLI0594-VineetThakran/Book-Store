package com.book.store.service.userservice;

import com.book.store.dto.userdto.LoginDTO;
import com.book.store.dto.userdto.UserDTO;
import com.book.store.model.usermodel.User;

public interface UserServiceI {
    public User registerUser(User user);
    public String loginUser(LoginDTO loginDTO);
}
