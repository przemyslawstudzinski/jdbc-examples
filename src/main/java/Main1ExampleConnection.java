
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
    //System.out.println(TimeZone.getDefault().getID());
*/
public class Main1ExampleConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/library?serverTimezone=Europe/Warsaw";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) throws SQLException {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            // executeQuery() używamy dla SELECT, dla INSERT, UPDATE, DELETE (DML) executeUpdate()
            // resultSet.next() zwraca true jeśli jest kolejny rekord lub zwraca false jesli nie ma więcej wierszy
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book;");
            while (resultSet.next()) {
                String title = resultSet.getString(1);
                System.out.println(String.format("Tytuł: %s", title));
            }
        } catch (SQLException ex) {
            System.err.println("MySQL error code: " + ex.getErrorCode());
            ex.printStackTrace();
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

    }

}
