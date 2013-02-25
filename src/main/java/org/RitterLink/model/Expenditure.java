package org.RitterLink.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
<<<<<<< HEAD
import javax.persistence.Transient;
=======
>>>>>>> db4fd4492fe5b8a26f168b6f3aedc76c7d9849d1
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
<<<<<<< HEAD
import javax.xml.bind.annotation.XmlElement;
=======
>>>>>>> db4fd4492fe5b8a26f168b6f3aedc76c7d9849d1
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

<<<<<<< HEAD
=======

>>>>>>> db4fd4492fe5b8a26f168b6f3aedc76c7d9849d1
	@NotNull
	@NotEmpty
	private String description;

<<<<<<< HEAD
=======

>>>>>>> db4fd4492fe5b8a26f168b6f3aedc76c7d9849d1
	@NotNull
	@Column(name = "soll")
	private String soll;
	
	@NotNull
	@Column(name = "haben")
	private String haben;
<<<<<<< HEAD
=======
	
	@NotNull
	@Column(name = "realm")
	private String realm;
>>>>>>> db4fd4492fe5b8a26f168b6f3aedc76c7d9849d1

	@ManyToOne
    @JoinColumn(name="SUBACCOUNT_ID")
	private SubAccount subAccount;
<<<<<<< HEAD
	
	@Transient
	@XmlElement
	private String Account;
	
	@Transient
	@XmlElement
	private String Subaccount;
=======
>>>>>>> db4fd4492fe5b8a26f168b6f3aedc76c7d9849d1

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

<<<<<<< HEAD
	public String getAccount() {
		return this.getSubAccount().getAccount().getLabel();
	}

	public void setAccount(String account) {
		Account = account;
	}

	public String getSubaccount() {
		return this.getSubAccount().getLabel();
		
	}

	public void setSubaccount(String subaccount) {
		Subaccount = subaccount;
=======
	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
>>>>>>> db4fd4492fe5b8a26f168b6f3aedc76c7d9849d1
	}






}
