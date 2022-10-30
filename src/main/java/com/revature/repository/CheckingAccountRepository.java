package com.revature.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.CheckingAccount;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Integer> {

	CheckingAccount getByAccountIdAndAccountBal(Integer accId, Integer accBal);

	Optional<CheckingAccount> findByUserId(Integer userId);

}
