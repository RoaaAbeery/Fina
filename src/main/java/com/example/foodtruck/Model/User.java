package com.example.foodtruck.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MyUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @NotEmpty(message = "name should not be empty")
//    @Column(columnDefinition = "varchar(50) not null")
//    @Pattern(regexp = "^[a-zA-Z ]+$")
    private  String userName;
    @NotNull(message = "password cannot be null")
    @Size(min = 6,message = "password must be more than 6 characters")
    @Column(columnDefinition = "varchar (20)")
    private String password;
    @Email(message = "Enter valid email")
    @NotEmpty(message = "Email should not be empty")
    @Column(columnDefinition = "varchar(30) not null unique")
    private  String email;
//    @NotEmpty(message = "phone should not be empty")
//    @Pattern(regexp = "^05\\d{8}$", message = "Invalid phone number format")
    @Column(columnDefinition = "varchar(11) not null ")
    private String phone;
//    @Pattern(regexp = "^(CUSTOMER|FOODTRUCK|ADMIN)$")
//    @Column(columnDefinition = "check (role=CUSTOMER or role=FOODTRUCK or role=ADMIN)")
    private String role;



    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private FoodTruck foodTruck;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Customer customer;

    //onetoone
//    @OneToOne(cascade = CascadeType.ALL , mappedBy = "user")
//    @PrimaryKeyJoinColumn
//    private Customer customer;
}
