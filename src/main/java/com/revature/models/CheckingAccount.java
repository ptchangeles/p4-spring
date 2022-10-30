package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "checking_acc")
public class CheckingAccount {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;
	private Integer userId;
	private Integer accountBal;
	private String status;
}
