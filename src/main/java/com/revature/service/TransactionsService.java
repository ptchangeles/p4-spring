package com.revature.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.Transactions;
import com.revature.repository.TransactionsRepository;

@Service
public class TransactionsService {

	private final TransactionsRepository transRepository;
	
	public TransactionsService(TransactionsRepository transRepository) {
		this.transRepository = transRepository;
	}
	
	public Transactions save(Transactions transaction) {
		return transRepository.save(transaction);
	}
	
	public List<Transactions> viewAll(){
		return transRepository.findAll();
	}
}
