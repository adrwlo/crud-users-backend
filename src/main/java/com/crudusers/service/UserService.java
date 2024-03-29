package com.crudusers.service;

import com.crudusers.entity.User;
import com.crudusers.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        List<User> sortedUsers = allUsers.stream()
                .sorted(Comparator.comparing(User::getId))
                .collect(Collectors.toList());

        return sortedUsers;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public String deleteUser(int id) {
        userRepository.deleteById(id);
        return "Deleted user with id " + id;
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setName(user.getName());
        existingUser.setSurname(user.getSurname());
        existingUser.setEmail(user.getEmail());
        existingUser.setNumber(user.getNumber());
        existingUser.setAge(user.getAge());
        return userRepository.save(existingUser);
    }

}
