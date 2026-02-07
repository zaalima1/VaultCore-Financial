package com.example.siya.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.siya.entity.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByAccountNumber(String accountNumber);

    Optional<Admin> findByMobileNumber(String mobileNumber);
}
