package com.bridgelabz.bookstoreproject.DTO;

import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserDTO {


    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Invalid name...!")
    private String firstName;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Invalid name...!")
    private String lastName;

    @NotNull(message = "state should not be null")
    private String state;

    public LocalDate dateOfBirth;

    @NotNull(message = "email should not be null")
    private String email;

    @NotNull(message = "password should not be null")
    private String password;

    private List<String> userEmail;

}
