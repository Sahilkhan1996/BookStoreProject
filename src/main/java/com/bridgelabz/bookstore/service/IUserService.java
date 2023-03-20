package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.UserModel;

import java.util.List;


public interface IUserService {

    String registerUser(UserDto userDto);

    List<UserModel> getAllUsers();

    UserModel getUserById(int id);

    UserModel getUserByEmail(String email);

    UserModel updateByEmail(String email, UserDto userDto);

    UserModel getUserByToken(String token);

    String deleteUserById(int id);

    String deleteUserByToken(String token);

    String changePassword(String oldp, String newp);

    String forgotPassword(String email, String newp);

    String login(LoginDto loginDto);


}
