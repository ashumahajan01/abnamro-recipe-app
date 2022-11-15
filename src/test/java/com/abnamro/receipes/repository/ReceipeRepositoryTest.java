
package com.abnamro.receipes.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

import com.abnamro.receipes.entity.Ingredients;
import com.abnamro.receipes.entity.Receipe;

@DataJpaTest
public class ReceipeRepositoryTest {

	@Mock
	private ReceipeRepository receipeRepository;

	@Test

	@Order(1)
	public void saveReceipeTest() {
		Mockito.when(receipeRepository.save(getReceipeObject())).thenReturn(getReceipeObject());
		assertNotNull(getReceipeObject());
		Assertions.assertTrue(getReceipeObject().getId() > 0);
	}

	@Test

	@Order(2)
	public void updateReceipeTest() {
		Optional<Receipe> updateIngredients = receipeRepository.findById(getReceipeObject().getId());
		if (updateIngredients.isPresent()) {
			receipeRepository.save(getReceipeObject());
			Assertions.assertEquals("tomato", getReceipeObject().getIngredients());
		}
	}

	@Test

	@Order(3)
	public void testGetAllRecipe() {
		Mockito.when(receipeRepository.findAll()).thenReturn(getAllReceipe());
	}

	private Receipe getReceipeObject() {
		Set<Ingredients> ingredients = new HashSet<Ingredients>();
		ingredients.add(new Ingredients(1l, "salmon"));
		Receipe recipe = new Receipe(1l, "chicken", 1, "oven", 1, ingredients);
		return recipe;
	}

	private List<Receipe> getAllReceipe() {
		List<Receipe> list = new ArrayList<Receipe>();
		list.add(getReceipeObject());
		return list;
	}

}
