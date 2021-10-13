package com.leonardo.rocha.gatewayDemo.controller;

import com.leonardo.rocha.gatewayDemo.data.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GatewayControllerTest {
    @InjectMocks
    GatewayController uut;

    @Mock
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTest() {
        ResponseEntity<String> response = uut.getTest();
        assertEquals(response.getBody(),"Hello, World!");
    }
}