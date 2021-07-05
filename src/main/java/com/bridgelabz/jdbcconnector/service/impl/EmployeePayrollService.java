package com.bridgelabz.jdbcconnector.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bridgelabz.jdbcconnector.dao.EmployeePayrollRepository;
import com.bridgelabz.jdbcconnector.dto.Employee;
import com.bridgelabz.jdbcconnector.exception.EmployeePayrollException;
import com.bridgelabz.jdbcconnector.exception.JdbcConnectorException;
import com.bridgelabz.jdbcconnector.service.IEmployeePayrollService;

public class EmployeePayrollService implements IEmployeePayrollService {
	private static final Logger LOG = LogManager.getLogger(EmployeePayrollService.class);
	private final EmployeePayrollRepository employeePayrollRepository = new EmployeePayrollRepository();

	@Override
	/**
	 * Function to get list of employee
	 */
	public List<Employee> getEmployeeList() throws EmployeePayrollException, SQLException, JdbcConnectorException {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList = employeePayrollRepository.getEmployeeList();
		LOG.debug("Number of employees retrieved " + employeeList.size());
		LOG.debug("List of employees retrieved " + employeeList.toString());
		return employeeList;
	}

}
