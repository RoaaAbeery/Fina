package com.example.foodtruck.Repository;

import com.example.foodtruck.Model.Customer;
import com.example.foodtruck.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomerById(Integer id);

}
