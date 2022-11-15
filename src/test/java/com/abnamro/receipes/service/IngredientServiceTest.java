package com.abnamro.receipes.service;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.abnamro.receipes.entity.Ingredients;
import com.abnamro.receipes.exception.ApiServerException;
import com.abnamro.receipes.exception.DataNotFoundException;
import com.abnamro.receipes.repository.IngredientsRepository;

@SpringBootTest
public class IngredientServiceTest {

	@Mock
	IngredientsService ingredientsService;

	@Mock
	private IngredientsRepository ingredientRepo;

	@Test
	public void getIngredientsFailWithNoDataTest() throws ApiServerException, DataNotFoundException {
		Mockito.when(ingredientRepo.findAll()).thenReturn(new ArrayList<>());
		ResponseEntity<List<Ingredients>> allIngredients = ingredientsService.getIngredients();
		assertNull(allIngredients);
	}

}
