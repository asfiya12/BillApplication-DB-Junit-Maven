package com.BillApplication;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
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

import com.BillApplication.Controller.BillController;
import com.BillApplication.Model.Bill;
import com.BillApplication.Model.Consumer;
import com.BillApplication.Service.BillService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BillController.class)
public class BillControllerTest {

	@MockBean
	private BillService billService;
	
	@Autowired
	private MockMvc mockMvc;
	
	Consumer consumer = new Consumer(1L, "Asfiya", "asfiya@gmail.com", null);

	@Test
	public void getAllBillsTest() throws Exception {
		List<Bill> mockUser = new ArrayList<>();
		
		Bill bill = new Bill(1L,100.00,LocalDateTime.now(), "bill created",consumer);
		mockUser.add(bill);
		Mockito.when(billService.getAllBills()).thenReturn(mockUser);

		mockMvc.perform(MockMvcRequestBuilders.get("/bill/all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
		Mockito.verify(billService, Mockito.times(1)).getAllBills();
	}
	
	@Test
	public void getBillByIdTest() throws Exception 
	{
		Long id = 1L;
		Bill bill = new Bill(1L,100.00,LocalDateTime.now(), "bill created",consumer);
		Mockito.when(billService.getById(1L)).thenReturn(bill);
		mockMvc.perform( MockMvcRequestBuilders
		      .get("/bill/{id}", id)
		      .accept(MediaType.APPLICATION_JSON))	      
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
		Mockito.verify(billService, Mockito.times(1)).getById(id);
	}
	
//	@Test
//	public void deleteBillTest(){
//	Long id = 1L;
//	doNothing().when(billService).deleteBill(id);
//	billService.deleteBill(id);
//	verify(billService,times(1)).deleteBill(id);
//	}
	
	 
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Test
	public void createBillTest() throws Exception 
	{
		 Bill newBill = new Bill(null, 200.00,null, "New Description",consumer);
		 Mockito.when(billService.createBill(Mockito.any(Bill.class))).thenReturn(true);
		 mockMvc.perform(MockMvcRequestBuilders.post("/bill")
		 .contentType(MediaType.APPLICATION_JSON))
		 .andExpect(MockMvcResultMatchers.status().isOk());
//		 .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(200.00))
//		 .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("New Description"));
		 Mockito.verify(billService, Mockito.times(1)).createBill(Mockito.any(Bill.class));
	}


}
