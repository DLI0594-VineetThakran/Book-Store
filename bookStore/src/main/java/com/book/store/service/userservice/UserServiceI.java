package com.book.store.service.userservice;

import com.book.store.dto.userdto.LoginDTO;
import com.book.store.dto.userdto.UserDTO;

public interface UserServiceI {
    public void registerUser(UserDTO userDTO);
    public String loginUser(LoginDTO loginDTO);
}
