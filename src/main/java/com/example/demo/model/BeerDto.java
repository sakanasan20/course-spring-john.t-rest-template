package com.example.demo.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BeerDto {

	private UUID id;
	
	private String name;
	
	private String style;
	
	private String upc;
	
}
