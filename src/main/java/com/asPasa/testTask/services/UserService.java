package com.asPasa.testTask.services;

import com.asPasa.testTask.exceptions.ApplicationException;
import com.asPasa.testTask.models.BookingSlot;
import com.asPasa.testTask.models.User;
import com.asPasa.testTask.repositories.UserRepository;
import com.asPasa.testTask.utils.BookingSlotComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User persistUser(User user){
        return userRepository.save(user);
    }
    public User createUser(User user){
        if(userRepository.existsByName(user.getName())){
            throw new ApplicationException("User already exists");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ApplicationException("User already exists");
        }
        return persistUser(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()->new ApplicationException("User not found with id: "+id));
    }

    public BookingSlot getNearestBooking(Long id){
        return findById(id).getSlots().stream().min(new BookingSlotComparator()).orElseThrow(()->new ApplicationException("User does not have any bookings"));
    }
    public User findByName(String name){
        return userRepository.findByName(name).orElseThrow(()->new ApplicationException("User with such username not found: "+name));
    }
    public UserDetailsService getUserDetailsService(){
        return this::findByName;
    }
    public User getCurrentUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByName(name);
    }
}
