package com.abnamro.receipes.request.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReceipeSearchCritria {

	private int noOfServers;
	private String instructions;
	private int receipeType;
	private int isIngredientsIncluded;
	private String ingredients;

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

	public int getIsIngredientsIncluded() {
		return isIngredientsIncluded;
	}

	public void setIsIngredientsIncluded(int isIngredientsIncluded) {
		this.isIngredientsIncluded = isIngredientsIncluded;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

}
