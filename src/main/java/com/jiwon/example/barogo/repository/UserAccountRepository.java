package com.jiwon.example.barogo.repository;

import com.jiwon.example.barogo.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
