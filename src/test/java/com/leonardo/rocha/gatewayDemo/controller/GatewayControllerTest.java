package com.leonardo.rocha.gatewayDemo.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;


public class GatewayControllerTest {
    GatewayController uut;
    @Before
    public void setUp() throws Exception {
        uut = new GatewayController();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTest() {
        ResponseEntity<String> response = uut.getTest();
        assertEquals(response.getBody(),"Hello, World!");
    }
}