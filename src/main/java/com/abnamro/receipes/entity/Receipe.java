package com.abnamro.receipes.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "receipe")
public class Receipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "noOfServers")
	private int noOfServers;

	@Column(name = "instructions")
	private String instructions;

	@Column(name = "receipeType")
	private int receipeType;

	public Receipe() {
		super();
	}

	public Receipe(Long id, String name, int noOfServers, String instructions, int receipeType,
			Set<Ingredients> ingredients) {
		super();
		this.id = id;
		this.name = name;
		this.noOfServers = noOfServers;
		this.instructions = instructions;
		this.receipeType = receipeType;
		this.ingredients = ingredients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "receipe_ingredient", joinColumns = @JoinColumn(name = "receipe_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private Set<Ingredients> ingredients;

}
