package org.RitterLink.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table
public class Account implements Serializable {
	

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	private int id;

	@NotNull
	@NotEmpty
	private String label;

	@Null
	@Column(name = "soll")
	private String soll;

	@Null
	@Column(name = "haben")
	private String haben;

	
	@OneToMany(mappedBy="account",fetch=FetchType.EAGER)
	@XmlTransient
    private List<SubAccount> subAccounts; 
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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
	@XmlTransient
	public List<SubAccount> getSubAccounts() {
		return subAccounts;
	}

	public void setSubAccounts(List<SubAccount> subAccounts) {
		this.subAccounts = subAccounts;
	}
}
