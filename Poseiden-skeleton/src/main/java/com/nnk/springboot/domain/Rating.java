package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicUpdate;
@Data
@DynamicUpdate
@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
	@Id
	Integer id;
	@Column
	String moodysRating;
	@Column
	String sandPRating;
	@Column
	String fitchRating;
	@Column
	Integer orderNumber;
}
