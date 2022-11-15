package com.abnamro.receipes.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abnamro.receipes.entity.Receipe;
import com.abnamro.receipes.exception.ApiServerException;
import com.abnamro.receipes.exception.DataNotFoundException;
import com.abnamro.receipes.repository.CustomSearchRepo;
import com.abnamro.receipes.repository.ReceipeRepository;
import com.abnamro.receipes.request.dto.ReceipeSearchCritria;

/**
 * @author : Ashu Mahajan This is service class has implementation of Recipe for
 *         add , update , search
 */
@Service
public class ReceipeService {

	private static final Logger logger = LoggerFactory.getLogger(ReceipeService.class);

	@Autowired
	private ReceipeRepository receipeRepo;

	@Autowired
	private CustomSearchRepo customerSearch;

	public ResponseEntity<List<Receipe>> getAllReceipe() throws ApiServerException, DataNotFoundException {
		try {
			logger.info("######IN ReceipeServiceImpl::getAllReceipe()");
			List<Receipe> receipeList = receipeRepo.findAll();
			if (receipeList.isEmpty()) {
				throw new DataNotFoundException();
			}
			logger.info("#####OUT ReceipeServiceImpl::getAllReceipe()");
			return ResponseEntity.ok(receipeList);
		} catch (Exception e) {
			logger.error("ReceipeServiceImpl : getAllReceipe() : " + e.getMessage());
			throw new ApiServerException();
		}
	}

	public ResponseEntity<Receipe> createReceipe(Receipe receipe) {
		try {
			logger.info("###### IN ReceipeServiceImpl::createReceipe()");
			receipeRepo.save(receipe);
			logger.info("##### OUT ReceipeServiceImpl::createReceipe()");
			return new ResponseEntity<>(receipe, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("ReceipeServiceImpl : createReceipe() : " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Receipe> updateReceipe(long id, Receipe receipe) {
		Optional<Receipe> ingredientsData = receipeRepo.findById(id);
		if (ingredientsData.isPresent()) {
			logger.info("###### IN ReceipeServiceImpl::updateReceipe()");
			return new ResponseEntity<>(receipeRepo.save(receipe), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<Receipe>> getAllReceipe(int receipeType)
			throws ApiServerException, DataNotFoundException {
		try {
			logger.info("###### IN ReceipeServiceImpl::getReceipe()");
			List<Receipe> receipeInfo = receipeRepo.findByReceipeType(receipeType);
			if (receipeInfo.isEmpty()) {
				throw new DataNotFoundException();
			}
			logger.info("OUT ReceipeServiceImpl::getReceipe()");
			return ResponseEntity.ok(receipeInfo);
		} catch (Exception e) {
			logger.error("ReceipeServiceImpl : getReceipe : " + e.getMessage());
			throw new ApiServerException();
		}
	}

	public ResponseEntity<List<Receipe>> getAllReceipe(int receipeType, int noOfServers)
			throws ApiServerException, DataNotFoundException {
		try {
			List<Receipe> receipeInfo = receipeRepo.findByReceipeTypeAndNoOfServers(receipeType, noOfServers);
			if (receipeInfo.isEmpty()) {
				throw new DataNotFoundException();
			}
			logger.info("OUT ReceipeServiceImpl::getReceipe()");
			return ResponseEntity.ok(receipeInfo);
		} catch (Exception e) {
			logger.error("ReceipeServiceImpl : getReceipe : " + e.getMessage());
			throw new ApiServerException();
		}
	}

	public Page<Receipe> getReceipe(ReceipeSearchCritria searchCriteria)
			throws ApiServerException, DataNotFoundException {
		logger.info("##### ReceipeServiceImpl :: getReceipe ###" + searchCriteria.toString());
		try {
			Page<Receipe> recipeList = customerSearch.findAllWithFilters(searchCriteria);
			if (recipeList.isEmpty()) {
				throw new DataNotFoundException();
			}
			return recipeList;
		} catch (Exception e) {
			logger.error("ReceipeServiceImpl : getReceipe : " + e.getMessage());
			throw new ApiServerException();
		}
	}

}
