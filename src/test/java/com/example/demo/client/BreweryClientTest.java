package com.example.demo.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.BeerDto;

@SpringBootTest
class BreweryClientTest {

	@Autowired
	BreweryClient breweryClient;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetBeer() {
		
		BeerDto beer = breweryClient.getBeer(UUID.randomUUID());
		assertNotNull(beer);
	}

}
