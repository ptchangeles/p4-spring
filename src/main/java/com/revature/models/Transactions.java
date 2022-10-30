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
@Table(name = "transactions")
public class Transactions {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transNo;
	private Integer check_accId;
	private Integer sav_accId;
    private Integer userId;
    private String transType;
}
