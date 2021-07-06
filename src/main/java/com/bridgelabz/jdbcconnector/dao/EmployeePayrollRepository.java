package com.bridgelabz.jdbcconnector.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bridgelabz.jdbcconnector.dto.Employee;
import com.bridgelabz.jdbcconnector.exception.EmployeePayrollException;
import com.bridgelabz.jdbcconnector.exception.JdbcConnectorException;
import com.bridgelabz.jdbcconnector.type.Gender;
import com.bridgelabz.jdbcconnector.utils.JdbcConnectionFactory;

public class EmployeePayrollRepository {
	private static final Logger LOG = LogManager.getLogger(EmployeePayrollRepository.class);
	private static EmployeePayrollRepository employeePayrollRepository;

	private EmployeePayrollRepository() {

	}

	public static EmployeePayrollRepository getInstance() {
		if (employeePayrollRepository == null) {
			employeePayrollRepository = new EmployeePayrollRepository();
		}
		return employeePayrollRepository;
	}

	/**
	 * To get the list of employees from the database
	 * 
	 * @return List<Employee>
	 * @throws SQLException
	 * @throws JdbcConnectorException
	 * @throws EmployeePayrollException
	 */
	public List<Employee> getEmployeeList() throws SQLException, JdbcConnectorException, EmployeePayrollException {
		String query = "select * from employee";
		try (Connection connection = JdbcConnectionFactory.getJdbcConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			List<Employee> employeeList = mapResultSetToEmployeeList(resultSet);
			return employeeList;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}
	}

	/**
	 * Function to update salary by name.
	 * 
	 * @param name
	 * @param salary
	 * @return int
	 * @throws JdbcConnectorException
	 * @throws SQLException
	 * @throws EmployeePayrollException
	 */
	public int updateSalaryByName(String name, Double salary)
			throws JdbcConnectorException, SQLException, EmployeePayrollException {
		try (Connection connection = JdbcConnectionFactory.getJdbcConnection()) {
			String query = "update payroll set salary=? where employee_id=(select id from employee where employee_name=?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, salary);
			preparedStatement.setString(2, name);
			int resultSet = preparedStatement.executeUpdate();
			return resultSet;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}
	}

	/**
	 * Function to get salary by name
	 * 
	 * @param name
	 * @return Double
	 * @throws JdbcConnectorException
	 * @throws SQLException
	 * @throws EmployeePayrollException
	 */
	public Double getSalaryByName(String name) throws JdbcConnectorException, SQLException, EmployeePayrollException {
		try (Connection connection = JdbcConnectionFactory.getJdbcConnection()) {
			String query = "select salary from payroll where employee_id=(select id from employee where employee_name=?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			Double salary = 0.0;
			while (resultSet.next()) {
				salary = resultSet.getDouble("salary");
			}
			return salary;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}
	}

	/**
	 * This method is written to get the list of employees in a given date range
	 * 
	 * @param startDate
	 * @param endDate
	 * @return List<Employee>
	 * @throws EmployeePayrollException
	 */
	public List<Employee> getEmployeeByStartDateRange(LocalDate startDate, LocalDate endDate)
			throws EmployeePayrollException {
		try (Connection connection = JdbcConnectionFactory.getJdbcConnection()) {
			String query = "select * from employee where start_date between ? and ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDate(1, Date.valueOf(startDate));
			preparedStatement.setDate(2, Date.valueOf(endDate));
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Employee> employeeList = mapResultSetToEmployeeList(resultSet);
			return employeeList;
		} catch (Exception e) {
			throw new EmployeePayrollException(e.getMessage());
		}

	}

	/**
	 * Function to map result set to employee list.
	 * 
	 * @param resultSet
	 * @return List<Employee>
	 * @throws SQLException
	 */
	private List<Employee> mapResultSetToEmployeeList(ResultSet resultSet) throws SQLException {
		List<Employee> employeeList = new ArrayList<Employee>();
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
			LOG.debug(resultSet.getInt("id") + " " + resultSet.getString("employee_name"));
		}
		return employeeList;
	}
}
