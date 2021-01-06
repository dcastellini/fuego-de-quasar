package ar.com.quasar;

import ar.com.quasar.controllers.ApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuasarApplicationTests {

	@Autowired
	private ApiController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
