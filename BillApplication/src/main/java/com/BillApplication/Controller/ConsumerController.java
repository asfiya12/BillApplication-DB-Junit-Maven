package com.BillApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BillApplication.Model.Consumer;
import com.BillApplication.Service.ConsumerService;


@RestController
public class ConsumerController {
	
	@Autowired
	private ConsumerService consumerService;
	
	@GetMapping("/consumers")
	public ResponseEntity<List<Consumer>> getAllConsumers(){
		List<Consumer> consumers = consumerService.getAllConsumers();
		return new ResponseEntity<>(consumers, HttpStatus.OK);
	}
	
	@GetMapping("/consumers/{id}")
	public ResponseEntity<Consumer> getConsumersById(@PathVariable Long id) {
		Consumer consumer = consumerService.getConsumersById(id);
		if(consumer != null) {
			return new ResponseEntity<>(consumer, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/consumer")
	public ResponseEntity<String> createConsumer(@RequestBody Consumer consumer) {
		 boolean created = consumerService.createConsumer(consumer);
		 if(created) {
			 return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created");
		 }else {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error Occured.");
		 }
	}

//	@DeleteMapping("/consumer/{id}")
//	public ResponseEntity<String> deleteConsumer(@PathVariable Long id){
//		boolean deleted = consumerService.deleteConsumer(id);
//		if(deleted) {
//			return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully!");
//		}else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not able to delete");
//		}
//	}
	
	@DeleteMapping("/consumer/{id}")
	public ResponseEntity<String> deleteConsumer(@PathVariable Long id){
		consumerService.deleteConsumer(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully!");

	}
	
}
