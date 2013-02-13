package org.RitterLink.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


import org.hibernate.validator.constraints.NotEmpty;



@Entity
@XmlRootElement
@Table
public class Expenditure implements Serializable  {

	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@NotEmpty
	@Column(name="datum")
	private String datum;


	@NotNull
	@NotEmpty
	private String description;


	@NotNull
	@NotEmpty
	@Column(name = "soll")
	private String soll;


	@ManyToOne
    @JoinColumn(name="ACCOUNT_ID")
	private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSoll() {
		return soll;
	}

	public void setSoll(String soll) {
		this.soll = soll;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


}
