package com.abnamro.receipes.repository;

import static com.abnamro.receipes.constants.Constants.INGREDIENTS;
import static com.abnamro.receipes.constants.Constants.INSTRUCTIONS;
import static com.abnamro.receipes.constants.Constants.NOOFSERVERS;
import static com.abnamro.receipes.constants.Constants.RECEIPETYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.abnamro.receipes.entity.Ingredients;
import com.abnamro.receipes.entity.Receipe;
import com.abnamro.receipes.request.dto.ReceipeSearchCritria;

/**
 * @author : Ashu Mahajan This is class which implementation of search recipes
 */
@Repository
public class CustomSearchRepo {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomSearchRepo.class);

	private final EntityManager entityManager;
	private final CriteriaBuilder criteriaBuilder;

	public CustomSearchRepo(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}

	/**
	 * This method fetch all recipes from database and send to client based of
	 * client request
	 * 
	 * @return Page<Receipe>
	 */
	public Page<Receipe> findAllWithFilters(ReceipeSearchCritria receipeSearchCritria) {
		CriteriaQuery<Receipe> criteriaQuery = criteriaBuilder.createQuery(Receipe.class);
		Root<Receipe> receipeRoot = criteriaQuery.from(Receipe.class);
		Predicate predicate = getPredicateWithMultipleCriteria(receipeSearchCritria, receipeRoot);
		criteriaQuery.where(predicate);
		TypedQuery<Receipe> typedQuery = entityManager.createQuery(criteriaQuery);
		return new PageImpl<>(typedQuery.getResultList());
	}

	private Predicate getPredicateWithMultipleCriteria(ReceipeSearchCritria receipeSearchCritria,
			Root<Receipe> receipeRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if (receipeSearchCritria.getNoOfServers() != 0) {
			predicates.add(getPredicateFornoOfServers(receipeSearchCritria.getNoOfServers(), receipeRoot));
		}
		if (receipeSearchCritria.getReceipeType() != 0) {
			predicates.add(getPredicateForRecipeType(receipeSearchCritria.getReceipeType(), receipeRoot));
		}
		if (Objects.nonNull(receipeSearchCritria.getInstructions())
				&& receipeSearchCritria.getIsIngredientsIncluded() == 0) {
			if (Objects.nonNull(receipeSearchCritria.getIngredients())) {
				predicates.add(getPredicateForInstructions(receipeSearchCritria.getInstructions(), receipeRoot));
				predicates.add(getPredicateForNonIngredients(receipeSearchCritria.getIngredients(), receipeRoot));
			}
		} else {
			if (Objects.nonNull(receipeSearchCritria.getInstructions())) {
				predicates.add(getPredicateForInstructions(receipeSearchCritria.getInstructions(), receipeRoot));
			}
			if (receipeRoot != null && Objects.nonNull(receipeSearchCritria.getIngredients())) {
				predicates
						.add(getPredicateForIngredientsIngredients(receipeSearchCritria.getIngredients(), receipeRoot));
			}
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

	/**
	 * This method is used to check ingredients of recipe based filter data
	 * 
	 * @return Predicate Object
	 */
	private Predicate getPredicateForIngredientsIngredients(String ingredients, Root<Receipe> receipeRoot) {
		Join<Receipe, Ingredients> joinCourse = receipeRoot.join(INGREDIENTS, JoinType.INNER);
		Predicate predicateForIngredients = null;
		if (joinCourse != null) {
			predicateForIngredients = criteriaBuilder.equal(joinCourse.get(INGREDIENTS), ingredients);
		}
		return predicateForIngredients;
	}

	/**
	 * This method is used to check ingredients of recipe based filter data
	 * 
	 * @return Predicate Object
	 */
	private Predicate getPredicateForNonIngredients(String ingredients, Root<Receipe> receipeRoot) {
		Join<Receipe, Ingredients> joinCourse = receipeRoot.join(INGREDIENTS, JoinType.INNER);
		Predicate predicateForIngredients = null;
		if (joinCourse != null) {
			predicateForIngredients = criteriaBuilder.notEqual(joinCourse.get(INGREDIENTS), ingredients);
		}
		return predicateForIngredients;
	}

	/**
	 * This method is used to check instructions of recipe based filter data
	 * 
	 * @return Predicate Object
	 */

	private Predicate getPredicateForInstructions(String instructions, Root<Receipe> receipeRoot) {
		Predicate predicateForInstructions = criteriaBuilder.equal(receipeRoot.get(INSTRUCTIONS), instructions);
		return predicateForInstructions;
	}

	/**
	 * This method is used to check recipetype of recipe based filter data
	 * 
	 * @return Predicate Object
	 */
	private Predicate getPredicateForRecipeType(int receipeType, Root<Receipe> receipeRoot) {
		Predicate predicateForreceipeType = criteriaBuilder.equal(receipeRoot.get(RECEIPETYPE), receipeType);
		return predicateForreceipeType;
	}

	/**
	 * This method is used to check noOfServers of recipe based filter data
	 * 
	 * @return Predicate Object
	 */
	private Predicate getPredicateFornoOfServers(int noOfServers, Root<Receipe> receipeRoot) {
		Predicate predicateFornoOfServers = criteriaBuilder.equal(receipeRoot.get(NOOFSERVERS), noOfServers);
		return predicateFornoOfServers;
	}

}
