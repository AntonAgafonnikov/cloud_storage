package com.example.cloud_storage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "_black_list_jwt_tokens")
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {
    @Id
    private String token;
}
