package com.revature.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.SavingsAccount;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Integer> {

	SavingsAccount getByAccountIdAndAccountBal(Integer accId, Integer accBal);

	Optional<SavingsAccount> findByUserId(Integer userId);

}
