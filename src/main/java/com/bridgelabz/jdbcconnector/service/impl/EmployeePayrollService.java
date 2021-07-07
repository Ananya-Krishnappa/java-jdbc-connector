package com.bridgelabz.jdbcconnector.service.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bridgelabz.jdbcconnector.dao.EmployeePayrollRepository;
import com.bridgelabz.jdbcconnector.dto.Employee;
import com.bridgelabz.jdbcconnector.dto.Payroll;
import com.bridgelabz.jdbcconnector.exception.EmployeePayrollException;
import com.bridgelabz.jdbcconnector.exception.JdbcConnectorException;
import com.bridgelabz.jdbcconnector.service.IEmployeePayrollService;
import com.bridgelabz.jdbcconnector.type.Gender;
import com.bridgelabz.jdbcconnector.type.SqlFunctions;

public class EmployeePayrollService implements IEmployeePayrollService {
	private static final Logger LOG = LogManager.getLogger(EmployeePayrollService.class);
	private EmployeePayrollRepository employeePayrollRepository;
	public List<Employee> employeeList = new ArrayList<Employee>();
	public Map<String, Double> employeeSalaryMap = new HashMap<String, Double>();

	public EmployeePayrollService() {
		employeePayrollRepository = EmployeePayrollRepository.getInstance();
	}

	public EmployeePayrollService(EmployeePayrollRepository employeePayrollRepository) {
		this.employeePayrollRepository = employeePayrollRepository;
	}

	public EmployeePayrollService(List<Employee> employeeList, Map<String, Double> employeeSalaryMap) {
		this.employeeList = employeeList;
		this.employeeSalaryMap = employeeSalaryMap;
		employeePayrollRepository = EmployeePayrollRepository.getInstance();
	}

	@Override
	/**
	 * Function to get list of employee
	 */
	public List<Employee> getEmployeeList() throws EmployeePayrollException, SQLException, JdbcConnectorException {
		try {
			employeeList = employeePayrollRepository.getEmployeeList();
			LOG.debug("Number of employees retrieved " + employeeList.size());
			LOG.debug("List of employees retrieved " + employeeList.toString());
			return employeeList;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}
	}

	/**
	 * Function to update the salary by name
	 */
	public int updateSalaryByName(String name, Double salary)
			throws JdbcConnectorException, SQLException, EmployeePayrollException {
		try {
			int result = employeePayrollRepository.updateSalaryByName(name, salary);
			if (result == 0) {
				throw new EmployeePayrollException("Could not update the salary for name " + name);
			}
			return result;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}
	}

	@Override
	/**
	 * This method is written to retrieve salary by name
	 */
	public Double getSalaryByName(String name) throws JdbcConnectorException, SQLException, EmployeePayrollException {
		try {
			Double result = employeePayrollRepository.getSalaryByName(name);
			employeeSalaryMap.put(name, result);
			return result;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}
	}

	/**
	 * Function to retrieve payroll by employee id
	 * 
	 * @param employeeId
	 * @return Payroll
	 * @throws JdbcConnectorException
	 * @throws SQLException
	 * @throws EmployeePayrollException
	 */
	public Payroll getPayrollByEmployeeId(int employeeId)
			throws JdbcConnectorException, SQLException, EmployeePayrollException {
		try {
			Payroll result = employeePayrollRepository.getPayrollByEmployeeId(employeeId);
			return result;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}
	}

	@Override
	/**
	 * Function to get the list of employees in a given date range
	 */
	public List<Employee> getEmployeeByStartDateRange(LocalDate startDate, LocalDate endDate)
			throws EmployeePayrollException {
		try {
			List<Employee> employeeList = new ArrayList<Employee>();
			employeeList = employeePayrollRepository.getEmployeeByStartDateRange(startDate, endDate);
			return employeeList;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}
	}

	@Override
	/**
	 * Function to find the sum,avg,min,max of salary
	 */
	public Double getResultForFunction(SqlFunctions sqlFunction, Gender gender) throws EmployeePayrollException {
		try {
			Double result = employeePayrollRepository.getResultForFunction(sqlFunction, gender);
			return result;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}
	}

	@Override
	/**
	 * Function to get the count by gender
	 */
	public int getCountByGender(Gender gender) throws EmployeePayrollException {
		try {
			int result = employeePayrollRepository.getCountByGender(gender);
			return result;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}
	}

	@Override
	/**
	 * Function to insert new employee
	 */
	public Employee addNewEmployee(Employee employee) throws EmployeePayrollException {
		try {
			Employee emp = employeePayrollRepository.addNewEmployee(employee);
			return emp;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}
	}

	@Override
	/**
	 * Function to retrieve employee and department list
	 */
	public List<Employee> getEmployeeAndDepartmentList() throws EmployeePayrollException {
		try {
			employeeList = employeePayrollRepository.getEmployeeAndDepartmentList();
			LOG.debug("Number of employees retrieved " + employeeList.size());
			LOG.debug("List of employees retrieved " + employeeList.toString());
			return employeeList;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}
	}
}
