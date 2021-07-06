package com.bridgelabz.jdbcconnector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bridgelabz.jdbcconnector.dto.Employee;
import com.bridgelabz.jdbcconnector.exception.EmployeePayrollException;
import com.bridgelabz.jdbcconnector.exception.JdbcConnectorException;
import com.bridgelabz.jdbcconnector.service.impl.EmployeePayrollService;

public class EmployeePayrollServiceTest {
	private EmployeePayrollService employeePayrollService;

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

	@Test
	public void givenSalaryAndEmpName_shouldUpdateSalaryForGivenEmpName()
			throws JdbcConnectorException, SQLException, EmployeePayrollException {
		int updatedRows = employeePayrollService.updateSalaryByName("ananya", 200000.0);
		assertTrue(updatedRows > 0);
	}

	@Test
	public void givenEmpName_shouldRetrieveSalary()
			throws JdbcConnectorException, SQLException, EmployeePayrollException {
		Double salary = employeePayrollService.getSalaryByName("ananya");
		assertEquals(salary, 200000.0);
	}

	@Test
	public void givenSalaryUpdated_shouldSyncWithDB()
			throws JdbcConnectorException, SQLException, EmployeePayrollException {
		Double salaryInDB = employeePayrollService.getSalaryByName("ananya");
		Double salaryInLocal = employeePayrollService.employeeSalaryMap.get("ananya");
		assertEquals(salaryInDB, salaryInLocal);
	}

	@Test
	public void givenDateRange_shouldReturnEmployeesJoinedInTheDateRange() throws EmployeePayrollException {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList = employeePayrollService.getEmployeeByStartDateRange(LocalDate.of(2021, 1, 1), LocalDate.now());
		assertEquals(2, employeeList.size());
	}
}
