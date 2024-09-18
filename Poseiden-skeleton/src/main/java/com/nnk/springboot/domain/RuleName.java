package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicUpdate;

@Data
@DynamicUpdate
@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
	@Id
	Integer id;
	@Column
	String name;
	@Column
	String description;
	@Column
	String json;
	@Column
	String template;
	@Column
	String sqlStr;
	@Column
	String sqlPart;
}
