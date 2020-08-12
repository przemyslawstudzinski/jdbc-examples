import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
    1.  Przygotowanie bazy danych.
    2.  Parametry konfiguracyjne: URL, USER, PASSWORD.
    3.  URL dla sterownika mysql-connector-java: "jdbc:mysql://localhost:3306/library".
    4.  Jawne podanie strefy czasowej w URL z uwagi na to, w Javie możemy mieć ustawioną inną niż systemowa.
*/
public class Main2ExampleTryWithResourcesConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library?serverTimezone=Europe/Warsaw";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        // Try-With-Resources
        try (
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book;");
            while (resultSet.next()) {
                String title = resultSet.getString(1);
                System.out.println(String.format("Tytuł: %s", title));
            }
            // Po wyjściu z bloku try wywoływane jest close() z interfejsu AutoCloseable.
        } catch (SQLException ex) {
            System.err.println("MySQL error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }
}
