package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.DTO.BookDetailsDTO;
import com.bridgelabz.bookstoreproject.DTO.LoginDTO;
import com.bridgelabz.bookstoreproject.DTO.ResponseDTO;
import com.bridgelabz.bookstoreproject.DTO.UserDTO;
import com.bridgelabz.bookstoreproject.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    String getMessage();

    String addUser(UserDTO usrDTO);

    Optional<UserModel> getUserModel(String token);

    Optional<UserModel> getUserById(int getId);

    List<UserModel> getListOfUsers();

    void deleteUser(int id);

    UserModel updateUser(int getId, UserDTO userDTO);

    Optional<UserModel> getUserByEmail(String email);

    String loginValidation(LoginDTO loginDTO);

    UserModel forgotPassword(String emailId, String newPassword);
}
