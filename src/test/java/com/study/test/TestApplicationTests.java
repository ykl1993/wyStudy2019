package com.study.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("a","ykl");
	}

}
