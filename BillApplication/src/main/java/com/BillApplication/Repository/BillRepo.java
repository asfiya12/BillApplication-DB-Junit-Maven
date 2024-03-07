package com.BillApplication.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.BillApplication.Model.Bill;


@Repository
public interface BillRepo extends CrudRepository<Bill, Long>{

}
