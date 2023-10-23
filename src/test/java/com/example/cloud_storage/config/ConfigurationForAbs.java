package com.example.cloud_storage.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "server.port = 8087",

                "spring.datasource.url=jdbc:tc:postgresql:16-alpine://postgres/postgres",
                "spring.datasource.username=postgres",
                "spring.datasource.password=postgres",

                "spring.jpa.hibernate.ddl-auto=create-drop",

                "spring.liquibase.enabled=false",
                "spring.liquibase.url=jdbc:tc:postgresql:16-alpine://postgres/postgres",
                "spring.liquibase.password=postgres",
                "spring.liquibase.user=postgres",
                "spring.liquibase.change-log=classpath:db.changelog/db.changelog-master.yaml"

        })
public abstract class ConfigurationForAbs {

    protected RequestSpecification requestSpecification;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestSpecification = new RequestSpecBuilder()
                .addHeader(
                        HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .disableCsrf()
                .build();
    }
}
