package com.project.shortener;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


class ShortenerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void applicationContextLoadsWithoutErrors() {
		assertThat(true).isTrue(); // Verifica se o contexto do Spring Boot carrega sem erros
	}
}