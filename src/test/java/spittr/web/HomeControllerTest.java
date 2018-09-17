package spittr.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class HomeControllerTest {

	@Test
	public void test() throws Exception {
    HomeController home = new HomeController();
    MockMvc mockMvc = standaloneSetup(home).build();
    mockMvc.perform(get("/home/get")).andExpect(view().name("home"));
  }

}
