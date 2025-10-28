package hu.hazazs.rest.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static hu.hazazs.rest.api.RestApiApplicationTests.ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "droid.id=" + ID)
@SuppressWarnings("unused")
class RestApiApplicationTests {

	static final String ID = "R2-D2";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testDroid() throws Exception {
		mockMvc.perform(get("/droid"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("R2-D2"));
	}

}