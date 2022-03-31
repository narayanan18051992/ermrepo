package com.erms.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Employee {
	
	@Id
	@Column
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String dob;

	@Column
	private int rating;
	
	@Column
	private String performanceLevel;
	
	@Column
	private String ageCategory;
	
	public Employee() {
		
	}
	
	public Employee(int id,String name,String dob,int rating) {
		this.id=id;
		this.name=name;
		this.dob=dob;
		this.rating=rating;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getPerformanceLevel() {
		return performanceLevel;
	}

	public void setPerformanceLevel(String performanceLevel) {
		this.performanceLevel = performanceLevel;
	}

	public String getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(String ageCategory) {
		this.ageCategory = ageCategory;
	}
	
	@Override
	public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Employee emp = (Employee) o;
	        return id == emp.id && Objects.equals(name, emp.name) && Objects.equals(dob, emp.dob) && rating==emp.rating;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id, name, dob, rating);
	}
	

}
