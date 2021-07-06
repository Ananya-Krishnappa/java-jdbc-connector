package com.bridgelabz.jdbcconnector.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.bridgelabz.jdbcconnector.dto.Employee;
import com.bridgelabz.jdbcconnector.exception.EmployeePayrollException;
import com.bridgelabz.jdbcconnector.exception.JdbcConnectorException;
import com.bridgelabz.jdbcconnector.type.Gender;
import com.bridgelabz.jdbcconnector.type.SqlFunctions;

public interface IEmployeePayrollService {
	public List<Employee> getEmployeeList() throws EmployeePayrollException, SQLException, JdbcConnectorException;

	public int updateSalaryByName(String name, Double salary)
			throws JdbcConnectorException, SQLException, EmployeePayrollException;

	public Double getSalaryByName(String name) throws JdbcConnectorException, SQLException, EmployeePayrollException;

	public List<Employee> getEmployeeByStartDateRange(LocalDate startDate, LocalDate endDate)
			throws EmployeePayrollException;

	public Double getResultForFunction(SqlFunctions sqlFunction, Gender gender) throws EmployeePayrollException;

	public int getCountByGender(Gender gender) throws EmployeePayrollException;
}
