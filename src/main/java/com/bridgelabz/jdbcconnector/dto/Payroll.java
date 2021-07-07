package com.bridgelabz.jdbcconnector.dto;

public class Payroll {
	public Integer id;
	public int employee_id;
	public Double salary;
	public Double deductions;
	public Double taxable_pay;
	public Double netpay;
	public Double tax;

	public Payroll() {

	}

	public Payroll(Integer id, int employee_id, Double salary, Double deductions, Double taxable_pay, Double netpay,
			Double tax) {
		super();
		this.id = id;
		this.employee_id = employee_id;
		this.salary = salary;
		this.deductions = deductions;
		this.taxable_pay = taxable_pay;
		this.netpay = netpay;
		this.tax = tax;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getDeductions() {
		return deductions;
	}

	public void setDeductions(Double deductions) {
		this.deductions = deductions;
	}

	public Double getTaxable_pay() {
		return taxable_pay;
	}

	public void setTaxable_pay(Double taxable_pay) {
		this.taxable_pay = taxable_pay;
	}

	public Double getNetpay() {
		return netpay;
	}

	public void setNetpay(Double netpay) {
		this.netpay = netpay;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	@Override
	public String toString() {
		return "Payroll [id=" + id + ", employee_id=" + employee_id + ", salary=" + salary + ", deductions="
				+ deductions + ", taxable_pay=" + taxable_pay + ", netpay=" + netpay + ", tax=" + tax + "]";
	}
}
