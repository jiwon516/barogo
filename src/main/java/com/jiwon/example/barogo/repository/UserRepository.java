package com.jiwon.example.barogo.repository;

import com.jiwon.example.barogo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
