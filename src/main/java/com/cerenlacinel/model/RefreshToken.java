package com.cerenlacinel.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity	
@Table(name = "refresh_token") // veritabanına adres olarak 
@Getter
@Setter
@NoArgsConstructor //parametresiz
@AllArgsConstructor //parametreli
public class RefreshToken extends BaseEntity{
	
	@Column(name = "refresh_token")
	private String refreshToken;
	
	@Column(name = "expired_date")
	private Date expiredDate;
	
	@ManyToOne
	private User user;

}
