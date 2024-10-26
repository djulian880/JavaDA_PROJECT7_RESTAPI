package com.nnk.springboot.domain;

//import org.springframework.beans.factory.annotation.Required;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicUpdate;


@Data
@DynamicUpdate
@Entity
@Table(name = "BidList")
public class BidList {
	@Id
	@Column(name ="BidListId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer BidListId;
	
	@Column
	@NotBlank(message = "Account is mandatory")
	String account;

	@Column
	@NotBlank(message = "Type is mandatory")
	String type;

	@Column
	@Digits(integer=10,fraction=0)
	Double bidQuantity;

	@Column
	@Digits(integer=10,fraction=0)
	Double askQuantity;

	@Column
	@Digits(integer=10,fraction=0)
	Double bid;

	@Column
	@Digits(integer=10,fraction=0)
	Double ask;

	@Column
	String benchmark;

	@Column
	Timestamp bidListDate;

	@Column
	String commentary;

	@Column
	String security;

	@Column
	String status;

	@Column
	String trader;

	@Column
	String book;

	@Column
	String creationName;

	@Column
	Timestamp creationDate;

	@Column
	String revisionName;

	@Column
	Timestamp revisionDate;

	@Column
	String dealName;

	@Column
	String dealType;

	@Column
	String sourceListId;

	@Column
	String side;
}
