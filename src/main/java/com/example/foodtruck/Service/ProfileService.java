package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.DTO.ProfileDTO;
import com.example.foodtruck.Model.Customer;
import com.example.foodtruck.Model.Profile;
import com.example.foodtruck.Model.User;
import com.example.foodtruck.Repository.AddressRepository;
import com.example.foodtruck.Repository.ProfileRepository;
import com.example.foodtruck.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public List<Profile> getProfile(){
        return profileRepository.findAll();
    }
    public void addProfile(ProfileDTO profileDTO){
        Customer customer = customerRepository.findCustomerById(profileDTO.getCustomer_id());
        if (customer == null) {
            throw new ApiException("the id customer not found");
        }

        Profile profile=new Profile(null, profileDTO.getDescription(), profileDTO.getAccountCreationDate(),null,customer);
        profileRepository.save(profile);
    }
    public void updateProfile(Integer auth,ProfileDTO profileDTO){
        Profile profile=profileRepository.findProfileById(auth);
        if (profile == null) {
            throw new ApiException("the id profile not found");
        }

        profile.setDescription(profileDTO.getDescription());
        profile.setAccountCreationDate(profileDTO.getAccountCreationDate());
        profileRepository.save(profile);


    }
    public void deleteProfile(Integer auth){
        Profile profile=profileRepository.findProfileById(auth);
        if (profile == null) {
            throw new ApiException("the id user not found");
        }
        profileRepository.delete(profile);
    }
    public Profile findUserById(Integer id){
        Customer customer= customerRepository.findCustomerById(id);
        if(customer==null){
            throw new ApiException("customer id not found");
        }
        return customer.getProfile();
    }

}
