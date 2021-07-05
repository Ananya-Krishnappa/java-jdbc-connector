package com.bridgelabz.jdbcconnector.service;

import java.sql.SQLException;
import java.util.List;

import com.bridgelabz.jdbcconnector.dto.Employee;
import com.bridgelabz.jdbcconnector.exception.EmployeePayrollException;
import com.bridgelabz.jdbcconnector.exception.JdbcConnectorException;

public interface IEmployeePayrollService {
	public List<Employee> getEmployeeList() throws EmployeePayrollException, SQLException, JdbcConnectorException;
}
