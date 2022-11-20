package com.abnamro.abnamro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.abnamro.receipes.controller.ReceipeController;

@SpringBootTest
class AbnamroApplicationTests {

	@Autowired
	private ReceipeController recipesController;

	@Test
	void contextLoads() {
		assertThat(recipesController).as("Application Context failed to load").isNotNull();
	}

}
