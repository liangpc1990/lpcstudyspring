package com.mock;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.List;

import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import junit.framework.Assert;

public class MockTest {
	
	
	public static void main(String[] args) {
		List<String> list = Mockito.mock(List.class);
		Mockito.when(list.get(0)).thenReturn("helloworld");
		String result = list.get(0);
		System.out.println(result);
		Mockito.verify(list).get(0);
		Assert.assertEquals("helloworld", result);
		System.out.println();
	}
}