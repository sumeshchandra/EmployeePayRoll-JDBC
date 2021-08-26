package com.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollServiceDB {
    public List<EmployeePayroll> readData() {
        List<EmployeePayroll> employeePayrolls = new ArrayList<>();
        String selectQuery = "SELECT * from employee_payroll";

        try {
            Connection connection =  this.getConnection();
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                employeePayrolls.add(new EmployeePayroll(id, name, salary));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return employeePayrolls;
    }

    private Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String username = "root";
        String password = "Password";
        System.out.println("Connection to database : " + jdbcUrl);
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Connection to the Data Base Successful : " + connection);
        return connection;

    }
}
