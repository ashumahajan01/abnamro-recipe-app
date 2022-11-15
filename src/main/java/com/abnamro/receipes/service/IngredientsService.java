package com.abnamro.receipes.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abnamro.receipes.entity.Ingredients;
import com.abnamro.receipes.exception.ApiServerException;
import com.abnamro.receipes.exception.DataNotFoundException;
import com.abnamro.receipes.repository.IngredientsRepository;

/**
 * @author : Ashu Mahajan This is service class has implementation of
 *         Ingredients for add , update , get all ingredients
 */
@Service
public class IngredientsService {

	private static final Logger logger = LoggerFactory.getLogger(IngredientsService.class);

	@Autowired
	private IngredientsRepository ingredientsRepo;

	public ResponseEntity<List<Ingredients>> getIngredients() throws ApiServerException, DataNotFoundException {
		try {
			logger.info("######IN IngredientsServiceImpl::getIngredients()");
			List<Ingredients> ingredientsList = ingredientsRepo.findAll();
			if (ingredientsList.isEmpty()) {
				throw new DataNotFoundException();
			}
			logger.info("OUT IngredientsServiceImpl::getIngredients()");
			return ResponseEntity.ok(ingredientsList);
		} catch (Exception e) {
			logger.error("IngredientsServiceImpl : getIngredients : " + e.getMessage());
			throw new ApiServerException();
		}
	}

	public ResponseEntity<Ingredients> createIngredients(Ingredients ingredients) {
		try {
			ingredientsRepo.save(ingredients);
			return new ResponseEntity<>(ingredients, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Ingredients> updateIngredients(long id, Ingredients ingredi) {
		Optional<Ingredients> ingredientsData = ingredientsRepo.findById(id);
		if (ingredientsData.isPresent()) {
			Ingredients _ingredients = ingredientsData.get();
			return new ResponseEntity<>(ingredientsRepo.save(_ingredients), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
