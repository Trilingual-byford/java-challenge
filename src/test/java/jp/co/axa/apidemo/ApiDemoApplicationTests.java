package jp.co.axa.apidemo;

import jp.co.axa.apidemo.controllers.EmployeeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApiDemoApplicationTests {
	@Autowired
	private EmployeeController controller;
	// A simple sanity check test that will fail if the application context cannot start.
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
