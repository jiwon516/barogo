package com.jiwon.example.barogo.service;

import com.jiwon.example.barogo.configuration.JwtTokenProvider;
import com.jiwon.example.barogo.dto.JwtToken;
import com.jiwon.example.barogo.dto.UserAccountDto;
import com.jiwon.example.barogo.entity.Member;
import com.jiwon.example.barogo.repository.MemberRepository;
import com.jiwon.example.barogo.global.exception.UserServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private UserService userService;

    private Member mockMember;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMember = new Member();
        mockMember.setUsername("testUser");
        mockMember.setPassword("encodedPassword");
        mockMember.setName("Test User");
        mockMember.setRole("USER");
    }

    @Test
    void createUser_Success() {
        UserAccountDto userAccountDto = new UserAccountDto("testUser", "password", "Test User");
        when(memberRepository.findByUsername(userAccountDto.getUserId())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userAccountDto.getUserPassword())).thenReturn("encodedPassword");

        ResponseEntity<?> response = userService.createUser(userAccountDto);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof Map);
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("회원가입이 완료되었습니다.", responseBody.get("responseText"));

        verify(memberRepository).save(any(Member.class));
        verify(passwordEncoder).encode(userAccountDto.getUserPassword());
        System.out.println(responseBody.get("responseText"));
    }

    @Test
    void createUser_Failure_UsernameDuplicated() {
        UserAccountDto userAccountDto = new UserAccountDto("testUser", "password", "Test User");
        when(memberRepository.findByUsername(userAccountDto.getUserId())).thenReturn(Optional.of(new Member()));

        ResponseEntity<?> response = userService.createUser(userAccountDto);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof Iterable);
        Iterable<String> responseBody = (Iterable<String>) response.getBody();
        assertTrue(((Collection<?>) responseBody).contains("사용중인 아이디 입니다."));

        verify(memberRepository, times(0)).save(any(Member.class));
    }

    @Test
    void signIn_Failure_InvalidPassword() {
        // Arrange
        String userId = "testUser";
        String password = "wrongPassword";
        when(memberRepository.findByUsername(userId)).thenReturn(Optional.of(mockMember));
        when(passwordEncoder.matches(password, mockMember.getPassword())).thenReturn(false);

        // Act & Assert
        UserServiceException exception = assertThrows(UserServiceException.class, () -> {
            userService.signIn(userId, password);
        });
        assertEquals("아이디, 패스워드가 올바르지 않습니다.", exception.getMessage());
        System.out.println(exception.getMessage());
    }

    @Test
    void signIn_Failure_UserNotFound() {
        // Arrange
        String userId = "nonExistentUser";
        String password = "password";
        when(memberRepository.findByUsername(userId)).thenReturn(Optional.empty());

        // Act & Assert
        UserServiceException exception = assertThrows(UserServiceException.class, () -> {
            userService.signIn(userId, password);
        });
        assertEquals("아이디, 패스워드가 올바르지 않습니다.", exception.getMessage());
        System.out.println(exception.getMessage());
    }
}