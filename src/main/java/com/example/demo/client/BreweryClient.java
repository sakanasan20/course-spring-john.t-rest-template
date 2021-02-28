package com.example.demo.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.BeerDto;

@ConfigurationProperties(value = "demo.brewery", ignoreUnknownFields = false)
@Component
public class BreweryClient {
	
	public final String BEER_PATH_V1 = "/api/v1/beers/";
	
	private String apihost;
	
	public final RestTemplate restTemplate;
	
	public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public BeerDto getBeer(UUID beerId) {
		return restTemplate.getForObject(apihost + BEER_PATH_V1 + beerId.toString(), BeerDto.class);
	}

	public void setApihost(String apihost) {
		this.apihost = apihost;
	}

}
