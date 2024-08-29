package com.book.store.service.adminservice;

import com.book.store.dto.admindto.AdminDto;
import com.book.store.dto.admindto.LoginDto;

public interface AdminServiceInterface {

    public void registerAdmin(AdminDto adminDto);
    public String loginAdmin(LoginDto loginDto);


}
