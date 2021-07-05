package com.bridgelabz.jdbcconnector.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.jdbcconnector.dto.Employee;
import com.bridgelabz.jdbcconnector.exception.JdbcConnectorException;
import com.bridgelabz.jdbcconnector.type.Gender;
import com.bridgelabz.jdbcconnector.utils.JdbcConnectionFactory;

public class EmployeePayrollRepository {

	/**
	 * To get the list of employees from the database
	 * 
	 * @return List<Employee>
	 * @throws SQLException
	 * @throws JdbcConnectorException
	 */
	public List<Employee> getEmployeeList() throws SQLException, JdbcConnectorException {
		List<Employee> employeeList = new ArrayList<Employee>();
		String query = "select * from employee";
		Connection connection = JdbcConnectionFactory.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			Employee employee = new Employee();
			employee.setAddress(resultSet.getString("address"));
			employee.setCity(resultSet.getString("city"));
			employee.setCompany_id(resultSet.getInt("company_id"));
			employee.setCountry(resultSet.getString("country"));
			employee.setEmployee_name(resultSet.getString("employee_name"));
			employee.setGender(Gender.valueOfLabel(resultSet.getString("gender")));
			employee.setId(resultSet.getInt("id"));
			employee.setPhone_num(resultSet.getString("phone_num"));
			employee.setStart_date(resultSet.getDate("start_date").toLocalDate());
			employeeList.add(employee);
			System.out.println(resultSet.getInt("id") + " " + resultSet.getString("employee_name"));
		}
		return employeeList;
	}

}
