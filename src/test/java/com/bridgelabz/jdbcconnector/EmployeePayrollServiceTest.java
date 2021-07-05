package com.bridgelabz.jdbcconnector;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bridgelabz.jdbcconnector.dto.Employee;
import com.bridgelabz.jdbcconnector.exception.EmployeePayrollException;
import com.bridgelabz.jdbcconnector.exception.JdbcConnectorException;
import com.bridgelabz.jdbcconnector.service.IEmployeePayrollService;
import com.bridgelabz.jdbcconnector.service.impl.EmployeePayrollService;

public class EmployeePayrollServiceTest {
	private IEmployeePayrollService employeePayrollService;

	@BeforeEach
	public void initialize() {
		employeePayrollService = new EmployeePayrollService();
	}

	@Test
	public void givenEmployeeRecordsInTable_shouldFetchEmployeeList()
			throws EmployeePayrollException, SQLException, JdbcConnectorException {
		List<Employee> employeeList = employeePayrollService.getEmployeeList();
		assertEquals(6, employeeList.size());
	}

}
