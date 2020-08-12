import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static config.DbConfiguration.PASSWORD;
import static config.DbConfiguration.URL;
import static config.DbConfiguration.USER;

public class MainEx2 {

    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)
        ) {
            updateSalary(connection);
            updateTitle(connection);
        } catch (SQLException ex) {
            System.err.println("MySQL error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }

    private static void updateSalary(final Connection connection) throws SQLException {
        final LocalDate localDate = LocalDate.now();
        final LocalDate localDateMinusTwoYear = localDate.minusYears(2);
        final Date date = Date.valueOf(localDateMinusTwoYear);
        System.out.println("Aktualna data minus dwa lata: " + date);

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE employee SET salary = salary + ? WHERE employment_date <= ?;")
        ) {
            preparedStatement.setInt(1, 500);  // 1 oznacza pierwszy znak zapytania w zapytaniu, 2 drugi itd.
            preparedStatement.setDate(2, date);

            final int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Liczba pracowników którym zwiększono wynagrodzenie o 500PLN : " + result);
        }
    }

    private static void updateTitle(final Connection connection) throws SQLException {
        final String newTitle = "Director";
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE employee SET title = ? WHERE id = ?;")
        ) {
           for (int i = 1; i <= 5; i++) {
                preparedStatement.setString(1, newTitle);
                preparedStatement.setInt(2, i);
                final int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    System.out.println("Zmieniono stanowisko pracownika o id: " + i + " na: " + newTitle);
                }
            }
        }
    }

}
