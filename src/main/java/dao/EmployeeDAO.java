package dao;

import domain.Employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CRUD Repository for Employee
 */
public class EmployeeDAO implements DAO {

    /**
     * Employee columns in DB.
     */
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String SALARY = "salary";
    private static final String EMPLOYMENT_DATE = "employment_date";
    private static final String TITLE = "title";

    private static Connection connection;

    public EmployeeDAO(final String url, final String user, final String password) throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }

    /**
     * Creates new Employee.
     * @param employee
     * @throws SQLException
     */
    public void create(final Employee employee) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO employee VALUES (?,?,?,?,?,?);")
        ) {
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setFloat(4, employee.getSalary());
            preparedStatement.setDate(5, employee.getEmploymentDate());
            preparedStatement.setString(6, employee.getTitle());

            final int result = preparedStatement.executeUpdate();
            if (result == 1) {
                System.out.println("Employee created!\t" + employee.toString());
            }
        }
    }

    /**
     * Returns Employee from DB.
     * @param id
     * @return Employee
     * @throws SQLException
     */
    public Employee read(final int id) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM employee WHERE id = ?;")
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final String firstName = resultSet.getString(FIRST_NAME);
                final String lastName = resultSet.getString(LAST_NAME);
                final float salary = resultSet.getFloat(SALARY);
                final Date employmentDate = resultSet.getDate(EMPLOYMENT_DATE);
                final String title = resultSet.getString(TITLE);

                final Employee employee = new Employee(id, firstName, lastName, salary, employmentDate, title);
                System.out.println("Return Employee: \t" + employee.toString());
                return employee;
            }
        }
        return null;
    }

    /**
     * Updates Employee.
     * @param employee
     * @throws SQLException
     */
    public void update(final Employee employee) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE employee SET first_name=?, last_name=?, salary=?, employment_date=?, title=? WHERE id = ?;")
        ) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setFloat(3, employee.getSalary());
            preparedStatement.setDate(4, employee.getEmploymentDate());
            preparedStatement.setString(5, employee.getTitle());

            preparedStatement.setInt(6, employee.getId());
            final int result = preparedStatement.executeUpdate();
            if (result == 1) {
                System.out.println("Employee witch id: " + employee.getId() + " updated.");
            }
        }
    }

    /**
     * Deletes Employee.
     * @param id
     * @throws SQLException
     */
    public void delete(final int id) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM employee WHERE id = ?;")
        ) {
            preparedStatement.setInt(1, id);
            final int result = preparedStatement.executeUpdate();
            if (result == 1) {
                System.out.println("Employee witch id: "  + id + " removed.");
            }
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Connection closed!");
        }
    }
}
