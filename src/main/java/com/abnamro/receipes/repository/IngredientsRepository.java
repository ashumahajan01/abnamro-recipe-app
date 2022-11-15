package com.abnamro.receipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abnamro.receipes.entity.Ingredients;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {

}
