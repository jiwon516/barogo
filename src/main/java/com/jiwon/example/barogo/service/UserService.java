package com.jiwon.example.barogo.service;


import com.jiwon.example.barogo.configuration.JwtTokenProvider;
import com.jiwon.example.barogo.dto.JwtToken;
import com.jiwon.example.barogo.dto.UserAccountDto;
import com.jiwon.example.barogo.entity.Member;
import com.jiwon.example.barogo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public boolean createUser(UserAccountDto userAccountDto) {
        if(memberRepository.findByUsername(userAccountDto.getUserId()).isPresent()) {
            return false;
        };
        Member user = new Member();

        user.setUsername(userAccountDto.getUserId());
        user.setPassword(passwordEncoder.encode(userAccountDto.getUserPassword()));
        user.setName(userAccountDto.getUserName());
        user.setRole("USER");
        memberRepository.save(user);
        return true;
    }

    @Transactional
    public ResponseEntity<?> signIn(String userId, String password) {
        Optional<Member> user = memberRepository.findByUsername(userId);
        if(user.isEmpty()){
            return ResponseEntity.badRequest().body(Collections.singletonList("아이디, 패스워드가 올바르지 않습니다."));
        }
        if(!passwordEncoder.matches(password, user.get().getPassword())){
            return ResponseEntity.badRequest().body(Collections.singletonList("아이디, 패스워드가 올바르지 않습니다."));
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.get().getUsername(), user.get().getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(jwtToken);
    }
}