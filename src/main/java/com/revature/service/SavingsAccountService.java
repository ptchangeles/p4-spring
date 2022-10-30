package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.exception.CheckingAccountNotFoundException;
import com.revature.exception.InsufficientFundsException;
import com.revature.exception.SavingsAccountNotFoundException;
import com.revature.models.CheckingAccount;
import com.revature.models.SavingsAccount;
import com.revature.repository.SavingsAccountRepository;

@Service
public class SavingsAccountService {

	private final SavingsAccountRepository savRepository;
	
	public SavingsAccountService(SavingsAccountRepository savRepository) {
		this.savRepository = savRepository;
	}
	
	public SavingsAccount save (SavingsAccount savAccount) {
		return savRepository.save(savAccount);
	}
	
	public SavingsAccount checkBalance(Integer accId, Integer accBal) {
		return savRepository.getByAccountIdAndAccountBal(accId, accBal);
	}
	
	public SavingsAccount viewSavingsAccountByAccountId(Integer accId) throws SavingsAccountNotFoundException {
		Optional<SavingsAccount> sa = savRepository.findById(accId);
		if (sa.isPresent()) {
			return sa.get();
		} else throw new SavingsAccountNotFoundException("No exisiting Savings Account");
	}	
	
	public SavingsAccount viewSavingsAccountByUserId(Integer userId) throws SavingsAccountNotFoundException{
		Optional<SavingsAccount> sa = savRepository.findByUserId(userId);
		if(sa.isPresent()) {
			return sa.get();
		} else throw new SavingsAccountNotFoundException("No exisiting Savings Account");
	}
	
	public List<SavingsAccount> viewAll(){
		return savRepository.findAll();
	}
	
	public void setStatus(Integer accId, String status) throws SavingsAccountNotFoundException {
		SavingsAccount updateStatus = this.viewSavingsAccountByAccountId(accId);
		updateStatus.setStatus(status);
	}
	
	public void deposit(Integer accId, Integer accBal) throws SavingsAccountNotFoundException {
		SavingsAccount ca = this.viewSavingsAccountByAccountId(accId);
		Integer currentBalance = ca.getAccountBal();
		Integer newBalance = currentBalance + accBal;
		ca.setAccountBal(newBalance);
	}
	
	public void withdrawal(Integer accId, Integer withdrawal) throws SavingsAccountNotFoundException, InsufficientFundsException {
		SavingsAccount ca = this.viewSavingsAccountByAccountId(accId);
		Integer currentBalance = ca.getAccountBal();
		if(currentBalance != 0 && withdrawal < currentBalance) {
			Integer newBalance = currentBalance - withdrawal;
			ca.setAccountBal(newBalance);
		} else throw new InsufficientFundsException("Insufficient Funds for Withdrawal");
	}
}
