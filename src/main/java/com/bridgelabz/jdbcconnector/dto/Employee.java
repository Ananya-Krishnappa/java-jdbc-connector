package com.bridgelabz.jdbcconnector.dto;

import java.time.LocalDate;

import com.bridgelabz.jdbcconnector.type.Gender;

public class Employee {
	public int id;
	public int company_id;
	public String employee_name;
	public Gender gender;
	public String phone_num;
	public LocalDate start_date;
	public String address;
	public String city;
	public String country;
	public Double salary;

	public Employee() {

	}

	public Employee(int id, int company_id, String employee_name, Gender gender, String phone_num, LocalDate start_date,
			String address, String city, String country) {
		super();
		this.id = id;
		this.company_id = company_id;
		this.employee_name = employee_name;
		this.gender = gender;
		this.phone_num = phone_num;
		this.start_date = start_date;
		this.address = address;
		this.city = city;
		this.country = country;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", company_id=" + company_id + ", employee_name=" + employee_name + ", gender="
				+ gender + ", phone_num=" + phone_num + ", start_date=" + start_date + ", address=" + address
				+ ", city=" + city + ", country=" + country + "]";
	}
}
