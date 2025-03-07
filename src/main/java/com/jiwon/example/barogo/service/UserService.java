package com.jiwon.example.barogo.service;


import com.jiwon.example.barogo.dto.UserAccountDto;
import com.jiwon.example.barogo.entity.UserAccount;
import com.jiwon.example.barogo.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserAccountRepository userAccountRepository;

    public void createUser(UserAccountDto userAccountDto) {
        UserAccount user = new UserAccount();

        user.setUserName(userAccountDto.getUserName());
        user.setUserId(userAccountDto.getUserId());
        user.setUserPassword(userAccountDto.getUserPassword());
        userAccountRepository.save(user);
    }
}
