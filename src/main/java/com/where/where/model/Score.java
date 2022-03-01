package com.where.where.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private float venueScore;

	private float coronaScore;

	@Column(name = "create_date")
	private String createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "place_id")
	private Place place;
}
