package com.bridgelabz.jdbcconnector.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bridgelabz.jdbcconnector.dao.EmployeePayrollRepository;
import com.bridgelabz.jdbcconnector.dto.Employee;
import com.bridgelabz.jdbcconnector.exception.EmployeePayrollException;
import com.bridgelabz.jdbcconnector.exception.JdbcConnectorException;
import com.bridgelabz.jdbcconnector.service.IEmployeePayrollService;

public class EmployeePayrollService implements IEmployeePayrollService {
	private static final Logger LOG = LogManager.getLogger(EmployeePayrollService.class);
	private EmployeePayrollRepository employeePayrollRepository;
	public List<Employee> employeeList = new ArrayList<Employee>();
	public Map<String, Double> employeeSalaryMap = new HashMap<String, Double>();

	public EmployeePayrollService() {
		employeePayrollRepository = EmployeePayrollRepository.getInstance();
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
		employeeList = employeePayrollRepository.getEmployeeList();
		LOG.debug("Number of employees retrieved " + employeeList.size());
		LOG.debug("List of employees retrieved " + employeeList.toString());
		return employeeList;
	}

	/**
	 * Function to update the salary by name
	 */
	public int updateSalaryByName(String name, Double salary)
			throws JdbcConnectorException, SQLException, EmployeePayrollException {
		int result = employeePayrollRepository.updateSalaryByName(name, salary);
		if (result == 0) {
			throw new EmployeePayrollException("Could not update the salary for name " + name);
		}
		return result;
	}

	@Override
	/**
	 * This method is written to retrieve salary by name
	 */
	public Double getSalaryByName(String name) throws JdbcConnectorException, SQLException, EmployeePayrollException {
		Double result = employeePayrollRepository.getSalaryByName(name);
		employeeSalaryMap.put(name, result);
		return result;
	}
}
