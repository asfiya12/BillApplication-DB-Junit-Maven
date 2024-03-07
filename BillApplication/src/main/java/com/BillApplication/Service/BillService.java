package com.BillApplication.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BillApplication.Exceptions.ResourceNotFoundException;
import com.BillApplication.Model.Bill;
import com.BillApplication.Repository.BillRepo;

@Service
public class BillService {

	@Autowired
	private BillRepo repo;

	public List<Bill> getAllBills() {
		List<Bill> bills = (List<Bill>) repo.findAll();
		return bills;
	}

	public Bill getById(Long id) {
		return repo.findById(id).get();
//		Bill bill = repo.findById(id).get();
//		if(bill != null) {
//			return bill;
//		}else {
//			throw new ResourceNotFoundException("err");
//		}
		
	}

	public boolean createBill(Bill bill) {
		double totalAmount = bill.getAmount()*1.20+1.8;
		bill.setCreatedDate(LocalDateTime.now());
		bill.setAmount(totalAmount);
		repo.save(bill);
		return true;
	}
	
	public boolean deleteBill(Long id) {
		repo.deleteById(id);
		return true;
	}

	public Bill updateBill(Long id, double newAmount) {
		Bill bill = repo.findById(id).get();
		double vatAmount = newAmount*(1+0.20)+1.8;
		bill.setAmount(vatAmount);
		return repo.save(bill);
	}

	public List<Bill> getBillsByMonth(int month, int year) {
		List<Bill> allBills = (List<Bill>) repo.findAll();
		List<Bill> monthlyBills = new ArrayList<>();
		
		LocalDateTime startDate = LocalDateTime.of(year, month, 1,0,0);
		LocalDateTime endDate = LocalDateTime.of(year, month+1, 1,0,0);
		
		for(Bill bill : allBills) {
			if(bill.getCreatedDate().isAfter(startDate) && bill.getCreatedDate().isBefore(endDate)) {
				monthlyBills.add(bill);
			}
		}
		return monthlyBills;
	}

}
