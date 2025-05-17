package com.picpay.domain.dtos;

import java.math.BigDecimal;

import com.picpay.domain.user.User;
import com.picpay.domain.user.UserType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UserDTO {
	
	private String firstName;
	private String lastName;
	private String document;
	private String email;
	private String password;
	private BigDecimal balance;

	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	public UserDTO() {
	}
	
	public UserDTO(User user) {
		firstName = user.getFirstName();
		lastName = user.getlastName();
		document = user.getdocument();
		balance = user.getBalance();
		email = user.getEmail();
		password = user.getPassword();
		userType = user.getUserType();

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
}
