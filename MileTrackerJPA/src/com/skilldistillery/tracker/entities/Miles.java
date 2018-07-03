package com.skilldistillery.tracker.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="miles_ran")
public class Miles {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name="miles_ran")
	private int miles;
	
	
	public int getMiles() {
		return miles;
	}
	public void setMiles(int miles) {
		this.miles = miles;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Miles [id=" + id + ", miles=" + miles + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + miles;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Miles other = (Miles) obj;
		if (id != other.id)
			return false;
		if (miles != other.miles)
			return false;
		return true;
	}
	
}
