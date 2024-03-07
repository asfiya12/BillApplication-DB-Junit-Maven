package com.BillApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BillApplication.Model.Consumer;

@Repository
public interface ConsumerRepo extends JpaRepository<Consumer,Long>{


	
}
