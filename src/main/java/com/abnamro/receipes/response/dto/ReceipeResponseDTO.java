package com.abnamro.receipes.response.dto;

import java.util.Set;

import com.abnamro.receipes.entity.Ingredients;

public class ReceipeResponseDTO {

	private String name;

	private int noOfServers;

	private String instructions;

	private int receipeType;

	private Set<Ingredients> ingredients;

	public ReceipeResponseDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoOfServers() {
		return noOfServers;
	}

	public void setNoOfServers(int noOfServers) {
		this.noOfServers = noOfServers;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public int getReceipeType() {
		return receipeType;
	}

	public void setReceipeType(int receipeType) {
		this.receipeType = receipeType;
	}

	public Set<Ingredients> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}

}
