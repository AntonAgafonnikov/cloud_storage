//package com.example.cloud_storage;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.test.web.client.TestRestTemplate;
////import org.springframework.http.ResponseEntity;
////import org.testcontainers.containers.GenericContainer;
////import org.testcontainers.junit.jupiter.Container;
////import org.testcontainers.junit.jupiter.Testcontainers;
//
//@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
////@Testcontainers
//class CloudStorageApplicationTests {
////    @Autowired
////    private TestRestTemplate testRestTemplate;
////
////    @Container
////    private static final GenericContainer<?> dataBaseTests =
////            new GenericContainer<>("postgres:latest").withExposedPorts(5432);
////
////    @Container
////    private static final GenericContainer<?> cloudStorageApplicationTests =
////            new GenericContainer<>("cloud_storage-cloudstorage:latest").withExposedPorts(8087).dependsOn(dataBaseTests);
//
//    @Test
//    void contextLoads() {
////        ResponseEntity<String> entityFromCloudStorageApplicationTests = testRestTemplate
////                .getForEntity("http://localhost:8087/login", String.class);
////
////        System.out.println("From Cloud: " + entityFromCloudStorageApplicationTests.getBody());
//    }
//}
