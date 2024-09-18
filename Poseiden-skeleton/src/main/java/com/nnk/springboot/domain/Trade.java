package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicUpdate;

@Data
@DynamicUpdate
@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
	@Id
	Integer tradeId;
	@Column
	String account;
	@Column
	String type;
	@Column
	Double buyQuantity;
	@Column
	Double sellQuantity;
	@Column
	Double buyPrice;
	@Column
	Double sellPrice;
	@Column
	String benchmark;
	@Column
	Timestamp tradeDate;
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
