package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer>{

}
