package com.abnamro.receipes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abnamro.receipes.entity.Receipe;
import com.abnamro.receipes.exception.ApiServerException;
import com.abnamro.receipes.exception.DataNotFoundException;
import com.abnamro.receipes.request.dto.ReceipeSearchCritria;
import com.abnamro.receipes.service.ReceipeService;

/**
 * @author : Ashu Mahajan This is controller class which have four end points
 *         for save, update, get All and search Recipe
 */
@RestController
@RequestMapping("/api/receipe")
public class ReceipeController {

	@Autowired
	private ReceipeService receipeService;

	/**
	 * This end point is used to save recipe
	 * 
	 * @return ResponseEntity<Receipe>
	 */
	@PostMapping("/save")
	public ResponseEntity<Receipe> createRecipe(@RequestBody Receipe receipe) {
		return receipeService.createReceipe(receipe);
	}

	/**
	 * This end point is used to get all recipes
	 * 
	 * @return ResponseEntity<Receipe>
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<Receipe>> getAllRecipes() throws ApiServerException, DataNotFoundException {
		return receipeService.getAllReceipe();
	}

	/**
	 * This end point is used to update recipe
	 * 
	 * @return ResponseEntity<Receipe>
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<Receipe> updateRecipe(@PathVariable("id") long id, @RequestBody Receipe receipe) {
		return receipeService.updateReceipe(id, receipe);
	}

	/**
	 * This end point is used to fetch all recipes base on user criteria
	 * 
	 * @return List<Receipe>
	 * @throws ApiServerException
	 * @throws DataNotFoundException
	 */
	@GetMapping(value = "/search")
	public ResponseEntity<Page<Receipe>> searchRecipe(@RequestBody ReceipeSearchCritria receipeSearchCriteria)
			throws ApiServerException, DataNotFoundException {
		return new ResponseEntity<>(receipeService.getReceipe(receipeSearchCriteria), HttpStatus.OK);
	}

}
