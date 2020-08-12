import dao.EmployeeDAO;
import domain.Employee;

import java.sql.Date;
import java.sql.SQLException;

import static config.DbConfiguration.PASSWORD;
import static config.DbConfiguration.URL;
import static config.DbConfiguration.USER;

public class MainEx3 {

    private static int ID = 1005;

    public static void main(String[] args) {
        try (
                EmployeeDAO employeeDAO = new EmployeeDAO(URL, USER, PASSWORD)
        ) {
            Employee newEmployee = new Employee(ID, "Jan", "Kowalski", 3233.11f,
                    Date.valueOf("2020-06-09"), "Manager");
            employeeDAO.create(newEmployee);

            Employee updatedEmployee = new Employee(ID, "Jan", "Kowalski", 5233.11f,
                    Date.valueOf("2020-06-09"), "HR Manager");
            employeeDAO.update(updatedEmployee);

            employeeDAO.read(newEmployee.getId());

            employeeDAO.delete(newEmployee.getId());
        } catch (SQLException ex) {
            System.err.println("MySQL error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }
}
