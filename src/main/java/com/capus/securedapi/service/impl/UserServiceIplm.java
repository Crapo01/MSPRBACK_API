package com.capus.securedapi.service.impl;

import com.capus.securedapi.dto.UserDetailsDto;
import com.capus.securedapi.entity.Role;
import com.capus.securedapi.entity.User;
import com.capus.securedapi.exceptions.ApiException;
import com.capus.securedapi.mapper.UserMapper;
import com.capus.securedapi.repository.UserRepository;
import com.capus.securedapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceIplm implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public List<UserDetailsDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::maptoUserDetailsDto).collect(Collectors.toList());
    }

    @Override
    public User updateUser(Long id,Set<Role> roles) throws ApiException {
        User user = userRepository
                .findById(id)
                .orElseThrow(()->new ApiException("Id:" + id + " Not found in database", HttpStatus.NOT_FOUND));
        user.setRoles(roles);
        userRepository.save(user);

        return user;
    }

    @Override
    public void deleteUser(Long id) throws ApiException {
        User user = userRepository
                .findById(id)
                .orElseThrow(()->new ApiException("Id:" + id + " Not found in database", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }
}
