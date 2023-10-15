package com.example.cloud_storage.config;

import com.example.cloud_storage.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class UserCache {
    @PersistenceContext
    private EntityManager entityManager;

    private final ConcurrentMap<String, User> store = new ConcurrentHashMap<>(256);

}
