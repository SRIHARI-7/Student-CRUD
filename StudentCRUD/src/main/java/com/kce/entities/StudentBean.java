package com.kce.entities;

public class StudentBean {
	
	private final int id;
	private String name;
	private String rollno;
	private String email;
	private String department;
	private String cgpa;
	
	public StudentBean(int id, String name, String rollno, String email, String department, String cgpa) {
		super();
		this.id = id;
		this.name = name;
		this.rollno = rollno;
		this.email = email;
		this.department = department;
		this.cgpa = cgpa;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRollno() {
		return rollno;
	}

	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCgpa() {
		return cgpa;
	}

	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}

	public int getId() {
		return id;
	}

	public StudentBean(int id) {
		super();
		this.id=id;
	}
	
	
	
	
	
}
