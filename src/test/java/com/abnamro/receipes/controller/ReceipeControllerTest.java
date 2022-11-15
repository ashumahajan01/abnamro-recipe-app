
package com.abnamro.receipes.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.abnamro.receipes.entity.Ingredients;
import com.abnamro.receipes.entity.Receipe;
import com.abnamro.receipes.exception.ApiServerException;
import com.abnamro.receipes.exception.DataNotFoundException;
import com.abnamro.receipes.request.dto.ReceipeSearchCritria;
import com.abnamro.receipes.service.ReceipeService;

@SpringBootTest
public class ReceipeControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private ReceipeController receipeController;

	@Mock
	private ReceipeService receipeService;

	@BeforeEach
	void setMockOutput() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(receipeController).build();
	}

	@Test
	public void getAllRecipeTest() throws DataNotFoundException, ApiServerException, Exception {
		Mockito.when(receipeService.getReceipe(getReceipeSearchCritria())).thenReturn(getAllReceipe());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/search/recipe").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private ReceipeSearchCritria getReceipeSearchCritria() {
		ReceipeSearchCritria criteria = new ReceipeSearchCritria();
		criteria.setReceipeType(1); // Non Veg
		criteria.setInstructions("fry");
		return criteria;
	}

	private Receipe getReceipeObject() {
		Set<Ingredients> ingredients = new HashSet<Ingredients>();
		ingredients.add(new Ingredients(1l, "salmon"));
		Receipe recipe = new Receipe(1l, "salmon", 1, "fry", 1, ingredients);
		return recipe;
	}

	private Page<Receipe> getAllReceipe() {
		List<Receipe> list = new ArrayList<>();
		list.add(getReceipeObject());
		PageRequest paginacao = PageRequest.of(1, 10);
		Page<Receipe> receipePages = new PageImpl<Receipe>(list, paginacao, list.size());
		return receipePages;
	}

}
