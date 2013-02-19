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
import javax.xml.bind.annotation.XmlTransient;


import org.hibernate.validator.constraints.NotEmpty;



@Entity
@XmlRootElement
@Table
public class Expenditure implements Serializable  {

	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@NotEmpty
	@Column(name="datum")
	private String datum;


	@NotNull
	@NotEmpty
	private String description;


	@NotNull
	@Column(name = "soll")
	private String soll;
	
	@NotNull
	@Column(name = "haben")
	private String haben;
	
	@NotNull
	@Column(name = "realm")
	private String realm;

	@ManyToOne
    @JoinColumn(name="SUBACCOUNT_ID")
	private SubAccount subAccount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	public String getHaben() {
		return haben;
	}

	public void setHaben(String haben) {
		this.haben = haben;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}
	@XmlTransient
	public SubAccount getSubAccount() {
		return subAccount;
	}

	public void setSubAccount(SubAccount subAccount) {
		this.subAccount = subAccount;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}






}
