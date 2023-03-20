package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.EmailSenderService;
import com.bridgelabz.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository repo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSenderService;


    @Override
    public String registerUser(UserDto userDto) {

        UserModel userModel = new UserModel(userDto);
        repo.save(userModel);
        String token = tokenUtil.createToken(userModel.getUserId());
        //emailSenderService.sendEmail(userModel.getEmail(), "User added ....","User name is :"+userModel.getFirstName()+" Token is :" +token);
        return token;
    }

    @Override
    public List<UserModel> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public UserModel getUserById(int id) {
        return repo.findById(id).orElseThrow(() -> new BookStoreException("Invalid ID"));

    }

    @Override
    public UserModel getUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public UserModel updateByEmail(String email, UserDto userDto) {

        UserModel newUserModel = repo.findByEmail(email);

        if (newUserModel == null) return null;

        newUserModel.updateUserModel(userDto);
        return repo.save(newUserModel);
    }

    @Override
    public UserModel getUserByToken(String token) {
        int id = tokenUtil.decodeToken(token);
        UserModel userModel = repo.findById(id).get();
        return userModel;
    }

    @Override
    public String deleteUserById(int id) {
        Optional<UserModel> userModel = repo.findById(id);
        if (userModel != null) {
            repo.deleteById(id);
            return "User deleted";
        }

        return "User not present so not deleted";
    }

    @Override
    public String deleteUserByToken(String token) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserModel> userModel = repo.findById(id);
        if (userModel != null) {
            repo.deleteById(id);
            return "User deleted";
        }

        return "User not present so not deleted";

    }

    @Override
    public String changePassword(String oldp, String newp) {
        List<UserModel> userModel = repo.findAll();

        for (UserModel um : userModel) {
            if (um.getPassword().equals(oldp)) {
                um.setPassword(newp);
                repo.save(um);
                return "password change successfully";
            }
        }
        return "Please enter correct password";
    }

    @Override
    public String forgotPassword(String email, String newp) {
        UserModel userModel = repo.findByEmail(email);
        if (userModel != null) {
            userModel.setPassword(newp);
            repo.save(userModel);
            return "password reset successfully";

        }
        return "Invalid email";
    }

    @Override
    public String login(LoginDto loginDto) {
        String email = loginDto.getEmail();
        UserModel userModels = repo.findByEmail(email);

        if (loginDto.getPassword().equals(userModels.getPassword())) {
            String token = tokenUtil.createToken(userModels.getUserId());
            return token;
        }
        return null;
    }
}
