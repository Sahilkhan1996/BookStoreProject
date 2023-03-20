package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.ResponseDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/bridgelabz")
@RestController
public class UserController {

    @Autowired
    IUserService service;

    //Api to register user
    @PostMapping("/register")
    ResponseEntity<ResponseDto> registerUser(@RequestBody UserDto userDto) {
        String token = service.registerUser(userDto);
        ResponseDto responseDto = new ResponseDto("User registered successfully ", token);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //Api to get all users
    @GetMapping("/getall")
    ResponseEntity<ResponseDto> getAllUsers() {
        List<UserModel> users = service.getAllUsers();
        ResponseDto responseDto = new ResponseDto("All users are ", users);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //Api to get user by id
    @GetMapping("/id/{id}")
    ResponseEntity<ResponseDto> getUserById(@PathVariable int id) {
        UserModel userModel = service.getUserById(id);
        ResponseDto responseDto = new ResponseDto("User data is :", userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //Api to get user by email id
    @GetMapping("/email/{email}")
    ResponseEntity<ResponseDto> getUserByEmail(@PathVariable String email) {
        UserModel userModel = service.getUserByEmail(email);
        ResponseDto responseDto = new ResponseDto("User data is :", userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //Api to update user by email id
    @PutMapping("/update/{email}")
    ResponseEntity<ResponseDto> updateByEmail(@PathVariable String email, @RequestBody UserDto userDto) {
        UserModel userModel = service.updateByEmail(email, userDto);
        ResponseDto responseDto = new ResponseDto("User data updated successfully  ", userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //Api to get user by token
    @GetMapping("/token/{token}")
    ResponseEntity<ResponseDto> getUserByToken(@PathVariable String token) {
        UserModel userModel = service.getUserByToken(token);
        ResponseDto responseDto = new ResponseDto("User data is ", userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //Api to delete user by id
    @DeleteMapping("/id/{id}")
    ResponseEntity<ResponseDto> deleteUserById(@PathVariable int id) {
        String userModel = service.deleteUserById(id);
        ResponseDto responseDto = new ResponseDto("User deleted or not :", userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //Api to delete user by token
    @DeleteMapping("/token/{token}")
    ResponseEntity<ResponseDto> deleteUserByToken(@PathVariable String token) {
        String userModel = service.deleteUserByToken(token);
        ResponseDto responseDto = new ResponseDto("User delete status :", userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //Api to change password
    @PutMapping("/oldp/{oldp}/newp/{newp}")
    ResponseEntity<ResponseDto> changePassword(@PathVariable String oldp, @PathVariable String newp) {
        String userModel = service.changePassword(oldp, newp);
        ResponseDto responseDto = new ResponseDto("Password change status", userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    //Api to forgot password
    @PutMapping("/email/{email}/newp/{newp}")
    ResponseEntity<ResponseDto> forgotPassword(@PathVariable String email, @PathVariable String newp) {
        String userModel = service.forgotPassword(email, newp);
        ResponseDto responseDto = new ResponseDto("Password forgot status", userModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    //Api to login
    @PostMapping("/login")
    String login(@RequestBody LoginDto loginDto) {
        String token = service.login(loginDto);
        return token;
    }


}
