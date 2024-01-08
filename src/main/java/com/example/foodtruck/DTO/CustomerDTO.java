package com.example.foodtruck.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private  String userName;
    private String password;
    @Email(message = "Enter valid email")
    @NotEmpty(message = "Email should not be empty")
    @Column(columnDefinition = "varchar(30) not null unique")
    private  String email;
    //    @NotEmpty(message = "phone should not be empty")
//    @Pattern(regexp = "^05\\d{8}$", message = "Invalid phone number format")
    @Column(columnDefinition = "varchar(11) not null ")
    private String phone;
    private String role;

//    private  String email;
//    private String phone;
//

//    private Integer t_id;
}
