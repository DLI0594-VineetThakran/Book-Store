package com.book.store.service.adminservice;

import com.book.store.dto.admindto.AdminDto;
import com.book.store.dto.admindto.LoginDto;

public interface AdminServiceImplementation {

    public void registerAdmin(AdminDto adminDto);
    public String loginAdmin(LoginDto loginDto);


}
