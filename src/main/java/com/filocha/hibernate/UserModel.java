package com.filocha.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "USERS")
public class UserModel {

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;
	
	@NotBlank
	@Column(name = "FirstName")
	private String firstName;
	
	@NotBlank
	@Column(name = "LastName")
	private String lastName;
	
	@NotBlank
	@Column(name = "Email")
	private String mail;
	
	@NotBlank
	@Column(name = "Password")
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
