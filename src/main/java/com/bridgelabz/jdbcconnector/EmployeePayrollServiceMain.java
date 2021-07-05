/**
 * Purpose:Employee Payroll Service
 * @author Ananya K
 * @version 1.0
 * @since 28/06/2021
 * 
 */
package com.bridgelabz.jdbcconnector;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bridgelabz.jdbcconnector.exception.EmployeePayrollException;
import com.bridgelabz.jdbcconnector.exception.JdbcConnectorException;
import com.bridgelabz.jdbcconnector.service.IEmployeePayrollService;
import com.bridgelabz.jdbcconnector.service.impl.EmployeePayrollService;

public class EmployeePayrollServiceMain {
	private static final Logger LOG = LogManager.getLogger(EmployeePayrollServiceMain.class);

	public static void main(String[] args) throws EmployeePayrollException, SQLException, JdbcConnectorException {
		IEmployeePayrollService employeePayrollService = new EmployeePayrollService();
		employeePayrollService.getEmployeeList();
	}
}
