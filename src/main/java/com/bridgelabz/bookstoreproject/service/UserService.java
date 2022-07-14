package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.DTO.LoginDTO;
import com.bridgelabz.bookstoreproject.DTO.ResponseDTO;
import com.bridgelabz.bookstoreproject.DTO.UserDTO;
import com.bridgelabz.bookstoreproject.exception.UserException;
import com.bridgelabz.bookstoreproject.model.UserModel;
import com.bridgelabz.bookstoreproject.repository.IUserRepository;
import com.bridgelabz.bookstoreproject.util.EmailSenderService;
import com.bridgelabz.bookstoreproject.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository repo;

    @Autowired
    TokenUtil tokenutil;

    @Autowired
    EmailSenderService sender;

    @Override
    public String getMessage() {

        return "Hello welcome to Book Store";
    }

    @Override
    public String addUser(UserDTO userDTO) {
        UserModel userModel = new UserModel(userDTO);
        repo.save(userModel);
        String token = tokenutil.createToken(userModel.getUserID());
        sender.sendEmail(userModel.getEmail(), "Test Email-BookStore",
                "The User Registered Successfully with the following details:" +
                        " \n " + userModel.toString());
        return token;
    }


    @Override
    public Optional<UserModel> getUserModel(String token) {
        int id = tokenutil.decodeToken(token);
        Optional<UserModel> userModel = repo.findById(id);
        if (userModel.isPresent()) {
            sender.sendEmail(userModel.get().getEmail(), "Test Email-BookStore",
                    "User retrieved successfully with the following details:" + "\n" + userModel);
            return userModel;
        } else {
            throw new UserException("User not present");
        }
    }

    @Override
    public Optional<UserModel> getUserById(int getId) {
        Optional<UserModel> userModel = repo.findById(getId);
        if (userModel.isPresent()) {
            return userModel;
        } else {
            return null;
        }
    }

    @Override
    public List<UserModel> getListOfUsers() {
        List<UserModel> users = repo.findAll();
        return users;
    }

    @Override
    public void deleteUser(int id) {
        Optional<UserModel> userModel = repo.findById(id);
        repo.deleteById(id);
        sender.sendEmail(userModel.get().getEmail(), "Test Mail-BookStore",
                "User deleted Successfully" + userModel.toString());
    }

    @Override
    public UserModel updateUser(int getId, UserDTO userDTO) {
        Optional<UserModel> newUser = repo.findById(getId);
        if (newUser.isPresent()) {
            newUser.get().setFirstName(userDTO.getFirstName());
            newUser.get().setLastName(userDTO.getLastName());
            newUser.get().setState(userDTO.getState());
            newUser.get().setEmail(userDTO.getEmail());
            newUser.get().setDateOfBirth(userDTO.getDateOfBirth());
            newUser.get().setPassword(userDTO.getPassword());
            repo.save(newUser.get());
            sender.sendEmail(newUser.get().getEmail(), "Test Mail-BookStore",
                    "Updated details of the user is: " + newUser);
            return newUser.get();
        } else {
            return null;
        }
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {

        Optional<UserModel> user = repo.findUserByEmail(email);
        return user;
    }

    @Override
    public String loginValidation(LoginDTO loginDTO) {
        String token;
        Optional<UserModel> isUserPresent = repo.findUserByEmail(loginDTO.getEmail());
        if (isUserPresent.isPresent()) {
            String password = isUserPresent.get().getPassword();
            if (password.equals(loginDTO.getPassword())) {
                token = tokenutil.createToken(isUserPresent.get().getUserID());
                return token;
            } else {
                throw new UserException("Password is incorrect");
            }
        } else {
            throw new UserException("User is invalid");
        }
    }

    @Override
    public UserModel forgotPassword(String emailId, String newPassword) {
        Optional<UserModel> isUserPresent = repo.findUserByEmail(emailId);
        if (isUserPresent.isPresent()) {
            isUserPresent.get().setPassword(newPassword);
            return repo.save(isUserPresent.get());
        } else {
            throw new UserException("Invalid User");
        }
    }
}