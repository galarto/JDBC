package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import static database.SqlRequests.*;

public class DataSource {
    private final static Logger LOGGER = Logger.getLogger(String.valueOf(DataSource.class));
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "09031999";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void createGuestsTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(CREATE_GUESTS_TABLE);
        } catch (SQLException e) {
            LOGGER.info("Table GUESTS was not created");

        }
    }

    public static void createTablesTable() {
        try (Connection connection = getConnection();
            Statement statement = connection.createStatement()) {

            statement.executeUpdate(CREATE_TABLES_TABLE);
        } catch (SQLException e) {
            LOGGER.info("Table TABLES was not created");

        }
    }

    public static void createReservationsTable() {
        try (Connection connection = getConnection();
            Statement statement = connection.createStatement()) {

            statement.executeUpdate(CREATE_RESERVATIONS_TABLE);
        } catch (SQLException e) {
            LOGGER.info("Table RESERVATIONS was not created");

        }
    }

    public static void dropGuestsTable() {
        try (Connection connection = getConnection();
            Statement statement = connection.createStatement()) {

            statement.executeUpdate(DROP_GUESTS_TABLE);
        } catch (SQLException e) {
            LOGGER.info("Table GUESTS was not deleted");
        }
    }

    public static void dropTablesTable() {
        try (Connection connection = getConnection();
            Statement statement = connection.createStatement()) {

            statement.executeUpdate(DROP_TABLES_TABLE);
        } catch (SQLException e) {
            LOGGER.info("Table TABLES was not deleted");
        }
    }

    public static void dropReservationsTable() {
        try (Connection connection = getConnection();
            Statement statement = connection.createStatement()) {

            statement.executeUpdate(DROP_RESERVATIONS_TABLE);
        }catch (SQLException e) {
            LOGGER.info("Table RESERVATIONS was not deleted");
        }
    }

}
