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
import com.revature.models.CheckingAccount;
import com.revature.service.CheckingAccountService;

@RestController
@RequestMapping("/checking")
@CrossOrigin("*")
public class CheckingAccountController {

	private final CheckingAccountService caService;
	
	public CheckingAccountController(CheckingAccountService caService) {
		this.caService = caService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CheckingAccount>> viewAll(){
		return ResponseEntity.ok(caService.viewAll());
	}
	
	@GetMapping("/me")
	public ResponseEntity<?> getCheckingAccount(
			@RequestParam(name="accId", required=true) Integer accId
			) throws CheckingAccountNotFoundException{
		 CheckingAccount ca = caService.viewCheckingAccountByAccountId(accId);
		return ResponseEntity.ok(ca);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCheckingAccountByUserId(
			@PathVariable("id") Integer userId
			) throws CheckingAccountNotFoundException{
		 CheckingAccount ca = caService.viewCheckingAccountByUserId(userId);
		return ResponseEntity.ok(ca);
	}
	
	@PutMapping("/setstatus")
	public ResponseEntity<?> setStatus(
			@RequestParam(name="accId", required=true) Integer accId,
			@RequestParam(name="status", required=true) String status
			) throws CheckingAccountNotFoundException{
		caService.setStatus(accId, status);
		return ResponseEntity.status(202).body("");
	}
	
	@PutMapping("/deposit")
	public ResponseEntity<?> deposit(
			@RequestParam(name="accId", required=true) Integer accId,
			@RequestParam(name="deposit", required=true) Integer deposit
			) throws CheckingAccountNotFoundException{
		caService.deposit(accId, deposit);
		return ResponseEntity.status(202).body("");
	}
	
	@PutMapping("/withdrawal")
	public ResponseEntity<?> withdrawal(
			@RequestParam(name="accId", required=true) Integer accId,
			@RequestParam(name="withdrawal", required=true) Integer withdrawal
			) throws CheckingAccountNotFoundException, InsufficientFundsException{
		caService.withdrawal(accId, withdrawal);
		return ResponseEntity.status(202).body("");
	}
}
