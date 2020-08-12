import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static config.DbConfiguration.PASSWORD;
import static config.DbConfiguration.URL;
import static config.DbConfiguration.USER;

public class MainEx1 {

    public static void main(String[] args)  {
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)
        ) {
            getAllEmployees(connection);
        } catch (SQLException ex) {
            System.err.println("MySQL error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }

    private static void getAllEmployees(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee;");
            while (resultSet.next()) {
                final int id = resultSet.getInt(1);
                final String firstName = resultSet.getString(2);
                final String lastName = resultSet.getString(3);
                final float salary = resultSet.getFloat(4);
                final Date employmentDate = resultSet.getDate(5);
                final String title = resultSet.getString(6);
                System.out.println(
                        String.format("Id: %d, First Name: %s, Last Name: %s, Salary: %f.2, Employment date: %s, Title: %s",
                                id, firstName, lastName, salary, employmentDate, title)
                );
            }
        }
    }

}
