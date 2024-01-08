package com.example.foodtruck.Controller;

import com.example.foodtruck.DTO.CustomerDTO;
import com.example.foodtruck.Model.User;
import com.example.foodtruck.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/get")
    public ResponseEntity GetAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity AddUser(@RequestBody @Valid CustomerDTO customerDTO){
        customerService.addCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body("added Client");
    }
//    @PutMapping("/put/{id}")
//    public ResponseEntity UpdateUser(@PathVariable Integer id,@RequestBody @Valid CustomerDTO cust){
//        customerService.updateCustomer(id,cust);
//        return ResponseEntity.status(HttpStatus.OK).body("update Client");
//    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete Client");
    }
    @GetMapping("/check")
    private ResponseEntity check(){

        return ResponseEntity.status(200).body(customerService.checkIfFoodTruckAvailable());
    }
    @GetMapping("/best/{number}/{re}")
    private ResponseEntity  best(@PathVariable Double number,@PathVariable Integer re){
        return ResponseEntity.status(200).body(customerService.HighestRating(number,re));
    }

}
