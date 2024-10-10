package com.nnk.springboot.domain;

import jakarta.validation.constraints.Digits;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
@DynamicUpdate
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column
	@Digits(integer=10,fraction=0)
	Integer curveId;
	@Column
	Timestamp asOfDate;
	@Column
	@Digits(integer=10,fraction=2,message = "Must be a floating point value")
	Double term;
	@Column
	@Digits(integer=10,fraction=2)
	Double value;
	@Column
	Timestamp creationDate;
}
