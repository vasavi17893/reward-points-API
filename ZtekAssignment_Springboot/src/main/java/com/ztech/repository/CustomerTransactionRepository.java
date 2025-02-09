package com.ztech.repository;

import com.ztech.entity.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction,Long> {

    List<CustomerTransaction> findByCustomerName(String customerName);
}
