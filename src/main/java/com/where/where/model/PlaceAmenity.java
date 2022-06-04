package com.where.where.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "place_amenities")
@AllArgsConstructor
@NoArgsConstructor
public class PlaceAmenity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "amenity_id")
	private Amenity amenity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "place_id")
	private Place place;
}
