package com.abnamro.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.abnamro.receipes.entity.Ingredients;
import com.abnamro.receipes.entity.Receipe;
import com.abnamro.receipes.exception.ApiServerException;
import com.abnamro.receipes.exception.DataNotFoundException;
import com.abnamro.receipes.repository.CustomSearchRepo;
import com.abnamro.receipes.repository.ReceipeRepository;
import com.abnamro.receipes.service.ReceipeService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ReceipeServiceTest {

	@Mock
	private ReceipeRepository receipeRepo;

	@Mock
	private CustomSearchRepo customerSearchRepo;

	@InjectMocks
	private ReceipeService service;

	public ReceipeServiceTest() {
	}

	@BeforeEach
	public void setup() {
		service = new ReceipeService();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllReceipe() throws ApiServerException, DataNotFoundException {
		when(receipeRepo.findAll()).thenReturn(getReceipeList());
		ResponseEntity<List<Receipe>> receipes = service.getAllReceipe();
		assertNotNull(receipes);
		assertEquals(receipes.getBody().get(0).getName(), "potato curry");
	}

//	@Test
//	public void getAllReceipesDataNotFound(){
//		when(receipeRepo.findAll()).thenReturn(new ArrayList<>());	
//		DataNotFoundException thrown = assertThrows(DataNotFoundException.class, () -> {
//			service.getAllReceipe();
//		}, "Data not found");
//		
//		assertEquals("Data not found", thrown.getMessage());
//	}

	@Test
	public void createReceipe() throws ApiServerException, DataNotFoundException {
		when(receipeRepo.save(recipeObject())).thenReturn(recipeObject());
		ResponseEntity<Receipe> savedReceipe = service.createReceipe(recipeObject());
		assertNotNull(savedReceipe);
		assertEquals(savedReceipe.getBody().getName(), "potato curry");
	}

	@Test
	public void updateReceipe() {
		when(receipeRepo.findById(1l)).thenReturn(getOptionalReceipeObject());
		ResponseEntity<Receipe> updateReceipe = service.updateReceipe(1l, updateRecipeObject());
		assertNotNull(updateReceipe);
	}
	
//	@Test
//	public void updateReceipeIDNotFound() {
//		when(receipeRepo.findById(1l)).thenReturn(Optional.empty());
//		DataNotFoundException thrown = Assertions.assertThrows(DataNotFoundException.class, () -> {
//			service.updateReceipe(1l, updateRecipeObject());
//		}, "ID not found");
//
//		assertEquals("ID not found", thrown.getMessage());
//	}

	@Test
	public void getAllReceipesWithSpecificType() throws ApiServerException, DataNotFoundException {
		when(receipeRepo.findByReceipeType(1)).thenReturn(getReceipeList());
		ResponseEntity<List<Receipe>> receipes = service.getAllReceipe(1);
		assertNotNull(receipes);
		assertEquals(receipes.getBody().get(0).getName(), "potato curry");
	}

	@Test
	public void getAllReceipesWithTypeAndServe() throws ApiServerException, DataNotFoundException {
		when(receipeRepo.findByReceipeTypeAndNoOfServers(1, 2)).thenReturn(getReceipeList());
		ResponseEntity<List<Receipe>> receipes = service.getAllReceipe(1, 2);
		assertNotNull(receipes);
		assertEquals(receipes.getBody().get(0).getName(), "potato curry");
	}

	private List<Receipe> getReceipeList() {
		List<Receipe> recipes = new ArrayList<Receipe>();

		Set<Ingredients> ingredients = new HashSet<Ingredients>();
		ingredients.add(new Ingredients(3L, "Potato"));
		recipes.add(new Receipe(1l, "potato curry", 2, "oven", 1, ingredients));

		return recipes;
	}

	private Receipe recipeObject() {
		Set<Ingredients> ingredients = new HashSet<Ingredients>();
		ingredients.add(new Ingredients(3L, "Potato"));
		return new Receipe(1l, "potato curry", 2, "oven", 1, ingredients);
	}

	private Receipe updateRecipeObject() {
		Receipe receipe = recipeObject();
		receipe.setName("tomato curry");
		return receipe;
	}

	private Optional<Receipe> getOptionalReceipeObject() {
		return Optional.of(recipeObject());
	}

}
