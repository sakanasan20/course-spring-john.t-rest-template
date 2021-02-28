package com.example.demo.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
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
	
	@Test
	void testPostBeer() {
		
		BeerDto beer = BeerDto.builder().name("New Beer").build();
		
		URI uri = breweryClient.postBeer(beer);
		
		assertNotNull(uri);
		
		System.out.println(uri.toString());
	}

}
