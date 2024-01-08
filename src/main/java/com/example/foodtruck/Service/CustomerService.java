package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.DTO.CustomerDTO;
import com.example.foodtruck.Model.Customer;
import com.example.foodtruck.Model.FoodTruck;
import com.example.foodtruck.Model.User;
import com.example.foodtruck.Repository.CustomerRepository;
import com.example.foodtruck.Repository.FoodTruckRepository;
import com.example.foodtruck.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private  final CustomerRepository customerRepository;
    private  final FoodTruckRepository foodTruckRepository;
    private final UserRepository userRepository;

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
    public void addCustomer(CustomerDTO customerDTO){

        User user =new User(null, customerDTO.getUserName(), customerDTO.getPassword(), customerDTO.getEmail(), customerDTO.getPhone(), customerDTO.getRole(), null,null);
        userRepository.save(user);
        Customer customer =new Customer(user.getId(),null,null,null,null,user);
        customerRepository.save(customer);
    }
//    public void updateCustomer(Integer id , CustomerDTO customerDTO) {
//        Customer oldCustomer = customerRepository.findCustomerById(id);
//        if (oldCustomer == null) {
//            throw new ApiException("Customer id not found");
//        }
//        oldCustomer.setEmail(customerDTO.getEmail());
//        oldCustomer.setPhone(customerDTO.getPhone());
//        oldCustomer.setPhone(customerDTO.getPhone());
//
//        customerRepository.save(oldCustomer);
//    }
    public void deleteCustomer(Integer auth){
        Customer customer = customerRepository.findCustomerById(auth);
        if (customer == null) {
            throw new ApiException("the id nt found");
        }
        customerRepository.delete(customer);
    }
    public List<FoodTruck> checkIfFoodTruckAvailable(){
       List<FoodTruck> foodTruck =foodTruckRepository.check();
        if (foodTruck == null) {
            throw new ApiException("the id nt found");
        }
        return foodTruck;
    }
    public List<FoodTruck> HighestRating(Double min, Integer ret) {
        List<FoodTruck> foodTruck = foodTruckRepository.findFByDay(min, ret);
        if (foodTruck == null) {
            throw new ApiException("food Truck id Uncorecct");

        }
        return foodTruck;
    }
//    public String logIn(String username, String password){
//        User user = userRepository.logIn(username,password);
//        if(user==null){
//            throw  new ApiException("username or password is incorrect");
//        }
//        return "Login successfully";
//    }
//    public List<FoodTruck> ret(Integer num){
//        List<FoodTruck> foodTruck=foodTruckRepository.findFoodTotByDay(num);
//        if (foodTruck == null) {
//            throw new ApiException("food Truck id incorrect");
//        }
//        return foodTruck;
//    }
//    public String currentStatus(Integer id) {
//        FoodTruck foodTruck = foodTruckRepository.findFoodTruckById(id);
//        if (foodTruck == null) {
//            throw new ApiException("food truck id incorrect");
//
//        }
//        return "Current Status: " + foodTruck.getCond();
//    }

    }