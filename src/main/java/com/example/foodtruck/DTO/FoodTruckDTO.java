package com.example.foodtruck.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class FoodTruckDTO {
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
    //    @NotEmpty(message = "License should not be empty")
//    @Column(columnDefinition = "varchar(30) not null")
    private String License;
    private String name;
    private String startDate;
    //@NotNull(message = "number of day should not be empty")
//    @Column(columnDefinition = "int not null")
    private Integer NumberOfEmployee;
    //    @NotEmpty(message = "city should not be empty")
//    @Column(columnDefinition = "varchar(100) not null")
//    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String city;
    private String cond;
    //    @Column(columnDefinition = "int")
    private Integer count;
    //    @Column(columnDefinition = "int not null")
    private Double rating;
    private Integer Requests;
}
