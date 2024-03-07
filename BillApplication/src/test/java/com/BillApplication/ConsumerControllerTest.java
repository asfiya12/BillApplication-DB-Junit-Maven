package com.BillApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.BillApplication.Controller.ConsumerController;
import com.BillApplication.Model.Consumer;
import com.BillApplication.Service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ConsumerController.class)
public class ConsumerControllerTest {

	@MockBean
	private ConsumerService consumerService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllComsumerTest() throws Exception {
		List<Consumer> mockUser = new ArrayList<>();
		mockUser.add(new Consumer(1L, "Asfiya", "asfiya@gmail.com", null));
		Mockito.when(consumerService.getAllConsumers()).thenReturn(mockUser);

		mockMvc.perform(MockMvcRequestBuilders.get("/consumers").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Asfiya"));
		Mockito.verify(consumerService, Mockito.times(1)).getAllConsumers();
	}
	
	@Test
	public void getConsumersByIdTest() throws Exception 
	{
		Long id = 1L;
		Consumer consumer = new Consumer(1L, "Asfiya", "asfiya@gmail.com", null);
		Mockito.when(consumerService.getConsumersById(1L)).thenReturn((Consumer) consumer);
		when(consumerService.getConsumersById(1L)).thenReturn((Consumer) consumer);
		mockMvc.perform( MockMvcRequestBuilders
		      .get("/consumers/{id}", id)
		      .accept(MediaType.APPLICATION_JSON))	      
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
		Mockito.verify(consumerService, Mockito.times(1)).getConsumersById(1L);
	}
	
	@Test
	public void deleteConsumerTest(){
	Long consumerId = 1L;
	doNothing().when(consumerService).deleteConsumer(consumerId);
	consumerService.deleteConsumer(consumerId);
	verify(consumerService,times(1)).deleteConsumer(consumerId);
	}
	
	 
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Test
	public void createConsumerTest() throws Exception 
	{
		Consumer consumer =new Consumer(1L, "Asfiya", "asfiya@gmail.com", null);
		Mockito.when(consumerService.createConsumer(Mockito.any(Consumer.class))).thenReturn(true);
		mockMvc.perform( MockMvcRequestBuilders
		      .post("/consumer")
		      .content(asJsonString(consumer))
		      .contentType(MediaType.APPLICATION_JSON)
		      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated())
	      .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
//		System.out.println(consumer);
		
		verify(consumerService,times(1)).createConsumer(consumer);
	}

}
