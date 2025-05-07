package com.cerenlacinel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity	
@Table(name = "address") // veritabanına adres olarak 
@Getter
@Setter
@NoArgsConstructor //parametresiz
@AllArgsConstructor //parametreli


public class Address extends BaseEntity{

	@Column(name = "city")
	private String city; // şehir
	
	@Column(name = "district")
	private String district; // bölge
	
	@Column(name = "neighborhood")
	private String neighborhood; //komşu

	@Column(name = "street")
	private String street; //sokak
	
	
}
