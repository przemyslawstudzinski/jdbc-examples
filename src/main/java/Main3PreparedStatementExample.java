import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main3PreparedStatementExample {

    private static final String URL = "jdbc:mysql://localhost:3306/library?serverTimezone=Europe/Warsaw";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM book WHERE category = ?;")
        ) {
            preparedStatement.setString(1, "Bazy danych"); // 1 oznacza pierwszy znak zapytania ? w zapytaniu
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString(1);
                System.out.println(String.format("Tytuł: %s", title));
            }
            resultSet.close();
            preparedStatement.setString(1, "Programowanie java");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString(1);
                System.out.println(String.format("Tytuł: %s", title));
            }
        } catch (SQLException ex) {
            System.err.println("MySQL error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }
}
