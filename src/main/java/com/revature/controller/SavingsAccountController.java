package com.revature.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.CheckingAccountNotFoundException;
import com.revature.exception.InsufficientFundsException;
import com.revature.exception.SavingsAccountNotFoundException;
import com.revature.models.CheckingAccount;
import com.revature.models.SavingsAccount;
import com.revature.service.SavingsAccountService;

@RestController
@RequestMapping("/savings")
@CrossOrigin("*")
public class SavingsAccountController {

	private final SavingsAccountService saService;
	
	public SavingsAccountController(SavingsAccountService saService) {
		this.saService = saService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<SavingsAccount>> viewAll(){
		return ResponseEntity.ok(saService.viewAll());
	}
	
	@GetMapping("/me")
	public ResponseEntity<?> getSavingsAccount(
			@RequestParam(name="accId", required=true) Integer accId
			) throws SavingsAccountNotFoundException{
		 SavingsAccount sa = saService.viewSavingsAccountByAccountId(accId);
		return ResponseEntity.ok(sa);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getSavingsAccountByUserId(
			@PathVariable("id") Integer userId
			) throws SavingsAccountNotFoundException{
		 SavingsAccount sa = saService.viewSavingsAccountByUserId(userId);
		return ResponseEntity.ok(sa);
	}
	
	@PutMapping("/setstatus")
	public ResponseEntity<?> setStatus(
			@RequestParam(name="accId", required=true) Integer accId,
			@RequestParam(name="status", required=true) String status
			) throws SavingsAccountNotFoundException{
		saService.setStatus(accId, status);
		return ResponseEntity.status(202).body("");
	}
	
	@PutMapping("/deposit")
	public ResponseEntity<?> deposit(
			@RequestParam(name="accId", required=true) Integer accId,
			@RequestParam(name="deposit", required=true) Integer deposit
			) throws SavingsAccountNotFoundException{
		saService.deposit(accId, deposit);
		return ResponseEntity.status(202).body("");
	}
	
	@PutMapping("/withdrawal")
	public ResponseEntity<?> withdrawal(
			@RequestParam(name="accId", required=true) Integer accId,
			@RequestParam(name="withdrawal", required=true) Integer withdrawal
			) throws InsufficientFundsException, SavingsAccountNotFoundException{
		saService.withdrawal(accId, withdrawal);
		return ResponseEntity.status(202).body("");
	}
}
