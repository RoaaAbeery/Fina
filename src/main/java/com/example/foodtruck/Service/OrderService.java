package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.DTO.OrderDTO;
import com.example.foodtruck.Model.*;
import com.example.foodtruck.Repository.AddressRepository;
import com.example.foodtruck.Repository.CustomerRepository;
import com.example.foodtruck.Repository.FoodTruckRepository;
import com.example.foodtruck.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final FoodTruckRepository foodTruckRepository;
    public List<Orders> getAll(){
        return orderRepository.findAll();
    }
    public void addOrder(OrderDTO orderDTO){
        Customer customer = customerRepository.findCustomerById(orderDTO.getCustomer_id());
        if (customer == null) {
            throw new ApiException("the id user not found");
        }
        FoodTruck foodTruck=foodTruckRepository.findFoodTruckById(orderDTO.getFoodTruck_id());
        if (foodTruck == null) {
            throw new ApiException("the id foodTruck not found");
        }
        Address address=addressRepository.findAddressById(orderDTO.getAddress_id());
        if (address == null) {
            throw new ApiException("the id address not found");
        }
        if (customer.getId()!=address.getProfile().getCustomer().getId()) {
            throw new ApiException("the id user in the address not same id user");

        }
            Orders order = new Orders(null, orderDTO.getDate(),  orderDTO.getTotalPrice(), orderDTO.getOrderStatus(), orderDTO.getNote(), orderDTO.getDiscount(),customer, null, foodTruck, address,null);
        orderRepository.save(order);
    }
    public void updateOrder(Integer auth ,OrderDTO orderDTO) {
        Orders orders=orderRepository.findOrderById(auth);
        if (orders == null) {
            throw new ApiException("the id user not found");
        }
        orders.setDate(orderDTO.getDate());
//        orders.setNumberOfDay(orderDTO.getNumberOfDay());
        orders.setTotalPrice(orderDTO.getTotalPrice());
        orders.setOrderStatus(orderDTO.getOrderStatus());
        orders.setNote(orderDTO.getNote());
        orders.setDiscount(orderDTO.getDiscount());
        orderRepository.save(orders);

    }


    public void deleteOrder(Integer auth){
        Orders order = orderRepository.findOrderById(auth);
        if (order == null) {
            throw new ApiException("the id nt found");
        }
        orderRepository.delete(order);
    }
    public Set<Orders> findOrderById(Integer id){
        Customer customer= customerRepository.findCustomerById(id);
        if(customer==null){
            throw new ApiException(" customer id not found");
        }
        return customer.getOrders();
    }
    public Set<Orders> findFoodTruckById(Integer id){
        FoodTruck foodTruck=foodTruckRepository.findFoodTruckById(id);
        if(foodTruck==null){
            throw new ApiException(" food Truck id not found");
        }
        return foodTruck.getOrders();
    }
    public List<Orders> findOrdersByTotalPrice(Double totalPrice){
      List < Orders > orders=orderRepository.findOrdersByTotalPrice(totalPrice);
        if(orders==null){
            throw new ApiException(" orders not found");
        }
        return orders;
    }

    public List<Orders> findOrdersByDate(LocalDate start,LocalDate end){
        List<Orders> orders = orderRepository.findOrdersByDateBetween(start, end);
        if (orders==null){
            throw new ApiException("There is no orders for these dates");
        }
        return orders;
    }
    public List<Orders> findOrdersByDateAfter(LocalDate date){
        List<Orders> orders = orderRepository.findOrdersByDateAfter(date);
        if (orders==null){
            throw new ApiException("There is no order for these dates");
        }
        return orders;
    }

    public Orders findOrdersByDateEqual(LocalDate date){
        Orders orders =orderRepository.findOrdersByDateEquals(date);
        if (orders == null){
            throw new ApiException("Invalid dates or no orders available for this date");
        }
        return orders;
    }
//
//
//
//    public List<Orders> findOrdersBefore(LocalDate date){
////        List<Orders> orders = orderRepository.findOrdersByDateBefore(date);
//        if (orders==null){
//            throw new ApiException("There is no orders before this date");
//        }   return orders;
    }



