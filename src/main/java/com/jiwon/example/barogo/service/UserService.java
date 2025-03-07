package com.jiwon.example.barogo.service;


import com.jiwon.example.barogo.dto.UserDto;
import com.jiwon.example.barogo.entity.User;
import com.jiwon.example.barogo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserDto userDto) {
        User user = new User();

        user.setUserName(userDto.getUserName());
        user.setUserId(userDto.getUserId());
        user.setUserPassword(userDto.getUserPassword());
        userRepository.save(user);

        return user;
    }
}
