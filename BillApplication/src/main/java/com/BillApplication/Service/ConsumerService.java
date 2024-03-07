package com.BillApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BillApplication.Model.Consumer;
import com.BillApplication.Repository.ConsumerRepo;
@Service
public class ConsumerService {

	@Autowired
	private ConsumerRepo  consumerRepo;
	public List<Consumer> getAllConsumers() {
		return consumerRepo.findAll();
	}
	public Consumer getConsumersById(Long id) {
		return consumerRepo.findById(id).orElseThrow();
	}
	public boolean createConsumer(Consumer consumer) {
		 consumerRepo.save(consumer);
		 return true;
	}
//	public boolean deleteConsumer(Long id) {
//		Consumer consumer = consumerRepo.findById(id).orElse(null);
//		if(consumer != null) {
//			consumerRepo.delete(consumer);
//			return true;
//		}
//		return false;
//	}

	public void deleteConsumer(Long id) {
		Consumer consumer = consumerRepo.findById(id).orElse(null);
			consumerRepo.delete(consumer);
	}
}
