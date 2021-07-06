package com.bridgelabz.jdbcconnector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.bridgelabz.jdbcconnector.type.Gender;
import com.bridgelabz.jdbcconnector.type.SqlFunctions;

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
	public void givenNoEmployeeRecordsInTable_whenFetchEmployeeListInDateRange_thenExceptionThrownAndAssertionSucceeds() {
		Exception exception = assertThrows(EmployeePayrollException.class, () -> {
			employeePayrollService.getEmployeeByStartDateRange(LocalDate.now(), LocalDate.now());
		});
		String expectedMessage = "No employees found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void givenSalaryAndEmpName_shouldUpdateSalaryForGivenEmpName()
			throws JdbcConnectorException, SQLException, EmployeePayrollException {
		int updatedRows = employeePayrollService.updateSalaryByName("ananya", 200000.0);
		assertTrue(updatedRows > 0);
	}

	@Test
	public void whenUpdateSalaryForNonExistentUser_thenExceptionThrownAndAssertionSucceeds() {
		Exception exception = assertThrows(EmployeePayrollException.class, () -> {
			employeePayrollService.updateSalaryByName("ann", 200000.0);
		});
		String expectedMessage = "Could not update the salary for name";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void givenEmpName_shouldRetrieveSalary()
			throws JdbcConnectorException, SQLException, EmployeePayrollException {
		Double salary = employeePayrollService.getSalaryByName("ananya");
		assertEquals(salary, 200000.0);
	}

	@Test
	public void whenGetSalaryForNonExistentUser_thenExceptionThrownAndAssertionSucceeds() {
		Exception exception = assertThrows(EmployeePayrollException.class, () -> {
			employeePayrollService.getSalaryByName("jisha");
		});
		String expectedMessage = "Salary details could not be fetched for employee";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
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

	@Test
	public void givenEmployeeRecordsInTable_shouldReturnSumOfSalaryForTheGivenGender() throws EmployeePayrollException {
		Double result = employeePayrollService.getResultForFunction(SqlFunctions.SUM, Gender.FEMALE);
		assertEquals(result, 454674.0);
		Double result1 = employeePayrollService.getResultForFunction(SqlFunctions.SUM, Gender.MALE);
		assertEquals(result1, 1571961.0);
	}

	@Test
	public void givenEmployeeRecordsInTable_shouldReturnAvgOfSalaryForTheGivenGender() throws EmployeePayrollException {
		Double result = employeePayrollService.getResultForFunction(SqlFunctions.AVG, Gender.FEMALE);
		assertEquals(result, 151558.0);
		Double result1 = employeePayrollService.getResultForFunction(SqlFunctions.AVG, Gender.MALE);
		assertEquals(result1, 523987.0);
	}

	@Test
	public void givenEmployeeRecordsInTable_shouldReturnMaxOfSalaryForTheGivenGender() throws EmployeePayrollException {
		Double result = employeePayrollService.getResultForFunction(SqlFunctions.MAX, Gender.FEMALE);
		assertEquals(result, 230987.0);
		Double result1 = employeePayrollService.getResultForFunction(SqlFunctions.MAX, Gender.MALE);
		assertEquals(result1, 990987.0);
	}

	@Test
	public void givenEmployeeRecordsInTable_shouldReturnMinOfSalaryForTheGivenGender() throws EmployeePayrollException {
		Double result = employeePayrollService.getResultForFunction(SqlFunctions.MIN, Gender.FEMALE);
		assertEquals(result, 23687.0);
		Double result1 = employeePayrollService.getResultForFunction(SqlFunctions.MIN, Gender.MALE);
		assertEquals(result1, 234987.0);
	}

	@Test
	public void givenEmployeeRecordsInTable_shouldReturnCountOfGender() throws EmployeePayrollException {
		int result = employeePayrollService.getCountByGender(Gender.FEMALE);
		assertEquals(result, 3);
		int result1 = employeePayrollService.getCountByGender(Gender.MALE);
		assertEquals(result1, 3);
	}
}
