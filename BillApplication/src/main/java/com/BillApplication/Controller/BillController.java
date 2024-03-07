package com.BillApplication.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BillApplication.Exceptions.ResourceNotFoundException;
import com.BillApplication.Model.Bill;
import com.BillApplication.Service.BillService;

@RestController
public class BillController {
Logger logger = LoggerFactory.getLogger(BillController.class);
	
	@Autowired
	private BillService billService; 
	
	//GET - All Bills
	@GetMapping("/bill/all")
	public ResponseEntity<List<Bill>> getAllBills(){
		List<Bill> bills = billService.getAllBills();
		return new ResponseEntity<>(bills, HttpStatus.OK);
	}
	
	//GET - Bill By ID
	@GetMapping("/bill/{id}")
	public ResponseEntity<Bill> getById(@PathVariable Long id) {
		Bill bill = billService.getById(id);
		if(bill != null) {
			return new ResponseEntity<>(bill, HttpStatus.OK);
		}else {
//			throw new NoSuchElementException("err");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//POST - Add Bill
	@PostMapping("/bill")
	public ResponseEntity<String> createBill(@RequestBody Bill bill) {
		 boolean created = billService.createBill(bill);
		 if(created) {
			 logger.info("Creating bill...");
			 return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created");
		 }else {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error Occured.");
		 }
	}

	//GET - Bills By Month
	@GetMapping("/bill/{month}/{year}")
	public List<Bill> getBillsByMonth(@PathVariable int month, @PathVariable int year){
		return billService.getBillsByMonth(month,year);
	}

	//DELETE - remove Bill
	@DeleteMapping("/bill/{id}")
	public ResponseEntity<String> deleteBill(@PathVariable Long id){
		boolean deleted = billService.deleteBill(id);
		if(deleted) {
			return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully!");
		}else {
			return ResponseEntity.status(null).body("Not able to delete");
		}
	}
	
	//UPDATE - Change Amount
	@PutMapping("/bill/{id}/amount")
	public Bill updateBill(@PathVariable Long id, @RequestParam double newAmount) {
		return billService.updateBill(id, newAmount);
	}

}
