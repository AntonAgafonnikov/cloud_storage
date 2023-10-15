package com.example.cloud_storage.repository;

import com.example.cloud_storage.model.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListTokenRepository extends JpaRepository<JwtToken, String> {
}
