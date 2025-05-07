package com.cerenlacinel.model;

import java.math.BigDecimal;
import com.cerenlacinel.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity	
@Table(name = "account") // veritabanına adres olarak 
@Getter
@Setter
@NoArgsConstructor //parametresiz
@AllArgsConstructor //parametreli
public class Account extends BaseEntity{
	
    
	@Column(name = "account_No")
	private String accountNo;
	 
	@Column(name = "iban")
	private String iban;
	
	@Column(name = "amount")
	private BigDecimal amount; //para
	
	@Column(name = "currency_Type")
	@Enumerated(EnumType.STRING) // veritanına tl,usd olarak yansıyacak
	private CurrencyType currencyType;
}
