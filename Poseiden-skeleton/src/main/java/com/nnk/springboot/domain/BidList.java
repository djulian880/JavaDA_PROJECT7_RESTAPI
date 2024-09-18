package com.nnk.springboot.domain;

//import org.springframework.beans.factory.annotation.Required;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.DynamicUpdate;


@Data
@DynamicUpdate
@Entity
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields
	@Id
	Integer BidListId;
	
	@Column
	String account;
	@Column
	String type;
	@Column
	Double bidQuantity;
	@Column
	Double askQuantity;
	@Column
	Double bid;
	@Column
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
