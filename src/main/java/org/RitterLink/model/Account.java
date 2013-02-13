package org.RitterLink.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


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
	
	@NotNull
	@NotEmpty
	

	@OneToMany(fetch=FetchType.EAGER)
    private Set<Expenditure> expenditures; 

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
	/*
	public Set<Expenditure> getExpenditures() {
		return expenditures;
	}

	public void setExpenditures(Set<Expenditure> expenditures) {
		this.expenditures = expenditures;
	}
	*/

	public Set<Expenditure> getExpenditures() {
		return expenditures;
	}

	public void setExpenditures(Set<Expenditure> expenditures) {
		this.expenditures = expenditures;
	}
}
