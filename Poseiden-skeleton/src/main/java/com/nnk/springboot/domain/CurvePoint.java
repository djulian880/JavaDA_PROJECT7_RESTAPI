package com.nnk.springboot.domain;

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
	Integer id;
	@Column
	Integer curveId;
	@Column
	Timestamp asOfDate;
	@Column
	Double term;
	@Column
	Double value;
	@Column
	Timestamp creationDate;
}
