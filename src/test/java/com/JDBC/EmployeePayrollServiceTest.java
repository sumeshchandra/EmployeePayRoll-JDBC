package com.JDBC;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class EmployeePayrollServiceTest {
    EmployeePayrollService employeePayrollService = null;

    @Before
    public void setUp() {
        employeePayrollService = new EmployeePayrollService();
    }

    /**
     * this test case to test whether the number of records present in the database matches with the expected value
     */
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = null;
        try {
            employeePayrollData = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        } catch (EmployeePayrollException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(3, employeePayrollData.size());
    }

    /**
     * This test case is for, whether the salary is updated in the database and is synced with the DB
     * Read values, Update the salary ,Test database is correctly synced or not
     */
    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() throws EmployeePayrollException {
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        employeePayrollService.updateEmployeeSalary("Sumesh", 2100000);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
        Assert.assertTrue(result);
    }

    /**
     * This Test case to test whether the salary is updated in the database and is synced with the DB using JDBC PreparedStatement
     */
    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDBUsingPreparedStatement() throws EmployeePayrollException {
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        employeePayrollService.updateEmployeeSalaryUsingPreparedStatement("Bill", 150000);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Bill");
        Assert.assertTrue(result);
    }
}