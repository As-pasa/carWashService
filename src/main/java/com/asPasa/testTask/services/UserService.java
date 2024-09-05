package com.asPasa.testTask.services;

import com.asPasa.testTask.exceptions.ApplicationException;
import com.asPasa.testTask.models.BookingSlot;
import com.asPasa.testTask.models.RoleType;
import com.asPasa.testTask.models.User;
import com.asPasa.testTask.models.dto.management.users.UserDataResponse;
import com.asPasa.testTask.models.dto.management.users.UserUpdateRequest;
import com.asPasa.testTask.repositories.UserRepository;
import com.asPasa.testTask.utils.BookingSlotComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

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
    public void deleteByUsername(String username){
        User user = findByName(username);
        userRepository.deleteById(user.getId());
    }
    public UserDataResponse updateUser(UserUpdateRequest request){
        if(request.getEmail()!=null){
            if(userRepository.existsByEmail(request.getEmail())){
                throw new ApplicationException("user with such email already exists: "+request.getEmail());
            }
        }
        if(request.getEmail()!=null){
            if(userRepository.existsByName(request.getNewUsername())){
                throw new ApplicationException("user with such username already exists: "+request.getNewUsername());
            }
        }

        User user= findByName(request.getOldUsername());
        if(request.getNewUsername()!=null){
            user.setName(request.getNewUsername());
        }
        if(request.getPassword()!=null){
            user.setPassword(encoder.encode(request.getPassword()));
        }
        if(request.getEmail()!=null){
            user.setEmail(request.getEmail());
        }
        if(request.getRole()!=null){
            user.setRole(RoleType.valueOf(request.getRole()));
        }
        persistUser(user);
        return new UserDataResponse(user.getName(),user.getRole().name(),user.getEmail());
    }
}
