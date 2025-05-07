package com.cerenlacinel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity	
@Table(name = "gallerist_car",
uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id", "car_id"},name = "uq_gallerist_car")})
//tekrar tekrar aynı kayıtların yazılmasını engelledik
@Getter
@Setter
@NoArgsConstructor //parametresiz
@AllArgsConstructor //parametreli
public class GalleristCar extends BaseEntity{
	
	@ManyToOne
	private Gallerist gallerist;
	
	@ManyToOne
	private Car car;

}
