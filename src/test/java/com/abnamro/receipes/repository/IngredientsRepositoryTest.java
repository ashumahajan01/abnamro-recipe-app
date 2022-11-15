
package com.abnamro.receipes.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

import com.abnamro.receipes.entity.Ingredients;

@DataJpaTest
public class IngredientsRepositoryTest {

	@Mock
	private IngredientsRepository ingredientRepo;

	@Test
	@Order(1)
	public void saveIngredientsTest() {
		Ingredients ingredients = new Ingredients(1l, "tomato");
		ingredientRepo.save(ingredients);
		Assertions.assertTrue(ingredients.getId() > 0);
	}

	@Test
	@Order(2)
	public void updateIngredientsTest() {
		Ingredients ingredients = new Ingredients(1l, "tomato");
		Optional<Ingredients> updateIngredients = ingredientRepo.findById(ingredients.getId());
		if (updateIngredients.isPresent()) {
			ingredientRepo.save(ingredients);
			Assertions.assertEquals("tomato", ingredients.getIngredients());
		}
	}

}
