package com.capus.securedapi.service;

import com.capus.securedapi.dto.UserDetailsDto;
import com.capus.securedapi.dto.UserRoleUpdateDto;
import com.capus.securedapi.entity.Role;
import com.capus.securedapi.entity.User;
import com.capus.securedapi.exceptions.ApiException;


import java.util.List;
import java.util.Set;


public interface UserService {
    //User createUser(User user);
    List<UserDetailsDto> getAllUsers();
    User updateUser(Long id, Set<Role> roles) throws ApiException;
    void deleteUser(Long id) throws ApiException;
}
