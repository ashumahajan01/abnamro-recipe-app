package com.abnamro.receipes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredients {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String ingredients;

	public Ingredients() {
		super();
	}

	public Ingredients(Long id, String ingredients) {
		this.id = id;
		this.ingredients = ingredients;
	}

	public Ingredients(Long id) {
		this.id = id;
	}

	public Ingredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Ingredients [id=" + id + ", ingredients=" + ingredients + "]";
	}
	
	
}
