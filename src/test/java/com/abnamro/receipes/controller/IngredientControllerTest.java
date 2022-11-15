package com.abnamro.receipes.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.abnamro.receipes.entity.Ingredients;
import com.abnamro.receipes.exception.ApiServerException;
import com.abnamro.receipes.exception.DataNotFoundException;
import com.abnamro.receipes.repository.IngredientsRepository;
import com.abnamro.receipes.service.IngredientsService;

@SpringBootTest
public class IngredientControllerTest {

	private MockMvc mockMvc;

	@Mock
	private IngredientsRepository ingredientRepo;

	@InjectMocks
	IngredientController ingredientController;

	@Mock
	IngredientsService ingredientsService;

	@BeforeEach
	void setMockOutput() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
	}

	@Test
	public void getAllIngredeintsTest() throws DataNotFoundException, ApiServerException, Exception {
		Mockito.when(ingredientsService.getIngredients()).thenReturn(getIngredientsData());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/ingredients/all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private ResponseEntity<List<Ingredients>> getIngredientsData() {
		return ResponseEntity.of(Optional.of(Collections.singletonList(new Ingredients(1l, "tomato"))));
	}

}
