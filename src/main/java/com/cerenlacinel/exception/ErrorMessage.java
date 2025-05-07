package com.cerenlacinel.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	
	private MessageType messageType;

	private String ofStatic;
	
	
	public String prepareErrorMessage() {
		StringBuilder builder = new StringBuilder();    //stringleri birleştirmek için kullanılan sınıf
		builder.append(messageType.getMessage());
		if (this.ofStatic!=null) {
			builder.append(" : " + ofStatic);
		}
		return builder.toString();
	}	
}
	
