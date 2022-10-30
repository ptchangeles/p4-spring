package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.exception.CheckingAccountNotFoundException;
import com.revature.exception.InsufficientFundsException;
import com.revature.models.CheckingAccount;
import com.revature.repository.CheckingAccountRepository;

@Service
public class CheckingAccountService {

	private final CheckingAccountRepository checkRepository;
	
	public CheckingAccountService(CheckingAccountRepository checkRepository) {
		this.checkRepository = checkRepository;
	}
	
	public CheckingAccount save (CheckingAccount checkingAccount) {
		return checkRepository.save(checkingAccount);
	}
	
	public CheckingAccount checkBalance(Integer accId, Integer accBal) {
		return checkRepository.getByAccountIdAndAccountBal(accId, accBal);
	}
	
	public CheckingAccount viewCheckingAccountByAccountId(Integer accId) throws CheckingAccountNotFoundException {
		Optional<CheckingAccount> ca = checkRepository.findById(accId);
		if (ca.isPresent()) {
			return ca.get();
		} else throw new CheckingAccountNotFoundException("No exisiting Checking Account", null);
	}	
	
	public CheckingAccount viewCheckingAccountByUserId(Integer userId) throws CheckingAccountNotFoundException{
		Optional<CheckingAccount> ca = checkRepository.findByUserId(userId);
		if(ca.isPresent()) {
			return ca.get();
		} else throw new CheckingAccountNotFoundException("No exisiting Checking Account", null);
	}
	
	public List<CheckingAccount> viewAll(){
		return checkRepository.findAll();
	}
	
	public void setStatus(Integer accId, String status) throws CheckingAccountNotFoundException {
		CheckingAccount updateStatus = this.viewCheckingAccountByAccountId(accId);
		updateStatus.setStatus(status);
	}
	
	public void deposit(Integer accId, Integer deposit) throws CheckingAccountNotFoundException {
		CheckingAccount ca = this.viewCheckingAccountByAccountId(accId);
		Integer currentBalance = ca.getAccountBal();
		Integer newBalance = currentBalance + deposit;
		ca.setAccountBal(newBalance);
	}
	
	public void withdrawal(Integer accId, Integer withdrawal) throws CheckingAccountNotFoundException, InsufficientFundsException {
		CheckingAccount ca = this.viewCheckingAccountByAccountId(accId);
		Integer currentBalance = ca.getAccountBal();
		if(currentBalance != 0 && withdrawal < currentBalance) {
			Integer newBalance = currentBalance - withdrawal;
			ca.setAccountBal(newBalance);
		} else throw new InsufficientFundsException("Insufficient Funds for Withdrawal");
	}
}
