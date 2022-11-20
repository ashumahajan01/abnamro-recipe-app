
package com.abnamro.receipes.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.abnamro.receipes.entity.Ingredients;
import com.abnamro.receipes.entity.Receipe;
import com.abnamro.receipes.exception.ApiServerException;
import com.abnamro.receipes.exception.DataNotFoundException;
import com.abnamro.receipes.request.dto.ReceipeSearchCritria;
import com.abnamro.receipes.service.ReceipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class ReceipeControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private ReceipeController receipeController;

	@MockBean
	private ReceipeService receipeService;

	@Autowired
	private ObjectMapper objectMapper;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setMockOutput() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(receipeController).build();
	}

	@Test
	public void getAllRecipeTest() throws DataNotFoundException, ApiServerException, Exception {
		Mockito.when(receipeService.getReceipe(getReceipeSearchCritria())).thenReturn(getAllReceipe());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/receipe/all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void createRecipe() throws Exception {
		when(receipeService.createReceipe(any(Receipe.class))).thenReturn(saveUpdateRecipeData());
		when(receipeService.createReceipe(saveRecipe())).thenReturn(saveUpdateRecipeData());
		mockMvc.perform(MockMvcRequestBuilders.post("/api/receipe/save").content(asJsonString(saveRecipeObject()))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		Mockito.verify(receipeService).createReceipe(Mockito.any(Receipe.class));
	}

	@Test
	public void updateRecipe() throws Exception {
		when(receipeService.updateReceipe(anyLong(), any(Receipe.class))).thenReturn(saveUpdateRecipeData());
		this.mockMvc.perform(put("/api/receipe/update/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(saveRecipeObject()))).andExpect(status().isOk());
		Mockito.verify(receipeService).updateReceipe(anyLong(), any(Receipe.class));
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

	private Receipe saveRecipe() {
		Set<Ingredients> ingredients = new HashSet<Ingredients>();
		ingredients.add(new Ingredients(3L, "Potato"));
		return new Receipe(1L, "potato curry", 2, "oven", 1, ingredients);

	}

	private Receipe saveRecipeObject() {
		Set<Ingredients> ingredients = new HashSet<Ingredients>();
		ingredients.add(new Ingredients(3L, "Potato"));
		return new Receipe(1l, "potato curry", 2, "oven", 1, ingredients);

	}

	private ResponseEntity<Receipe> saveUpdateRecipeData() {
		return ResponseEntity.ok(this.saveRecipeObject());
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
