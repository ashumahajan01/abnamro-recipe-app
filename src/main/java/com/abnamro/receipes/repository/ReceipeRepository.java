package com.abnamro.receipes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abnamro.receipes.entity.Receipe;

public interface ReceipeRepository extends JpaRepository<Receipe, Long> {

	@Query(value = "SELECT re.NAME ,re.NO_OF_SERVERS , re.RECEIPE_TYPE  FROM RECEIPE AS re WHERE re.receipeType =:receipeType", nativeQuery = true)
	List<Object[]> getAllReceipe(@Param("receipeType") int receipeType);

	List<Receipe> findByReceipeType(@Param("receipeType") int receipeType);

	List<Receipe> findByReceipeTypeAndNoOfServers(@Param("receipeType") int receipeType,
			@Param("noOfServers") int noOfServers);

	@Query(value = "SELECT R.NAME ,R.INSTRUCTIONS , I.INGREDIENTS , R.NO_OF_SERVERS , R.RECEIPE_TYPE  FROM RECEIPE  R , INGREDIENTS I , RECEIPE_INGREDIENT RI WHERE R.ID  = RI.RECEIPE_ID AND I.ID =  RI.INGREDIENT_ID", nativeQuery = true)
	List<Object[]> getAllReceipe();

}
