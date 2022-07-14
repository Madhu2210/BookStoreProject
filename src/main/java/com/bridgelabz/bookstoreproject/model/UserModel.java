package com.bridgelabz.bookstoreproject.model;

import com.bridgelabz.bookstoreproject.DTO.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user_model")
@Data
@NoArgsConstructor
@ToString
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="id")
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String state;
    private LocalDate dateOfBirth;
    private String password;


    @ElementCollection
    @CollectionTable(name = "email", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "email")
    private List<String> UserEmail;

    public UserModel(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.state = userDTO.getState();
        this.dateOfBirth=userDTO.getDateOfBirth();
        this.password = userDTO.getPassword();
        this.UserEmail=userDTO.getUserEmail();
    }
}
