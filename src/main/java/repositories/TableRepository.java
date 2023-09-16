package repositories;

import database.DataSource;
import models.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.SqlRequests.ADD_TABLE;

public class TableRepository {

    public void add(Table table) {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(ADD_TABLE)) {
            statement.setInt(1, table.getCapacity());
            statement.setBoolean(2, table.isAvailable());
            statement.setInt(3, table.getNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Logger add(Table table)");
        }
    }

    public Table getTable(int id) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from tables where id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return new Table(resultSet.getInt("id"),
                        resultSet.getInt("capacity"),
                        resultSet.getBoolean("isAvailable"),
                        resultSet.getInt("number"));
            }
        } catch (SQLException e) {
            System.out.println("Logger getTable(int id)");
        }
        return null;
    }

    public void updateTable(Table table) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("update tables set(capacity = ?, " +
                "isAvailable = ?, number = ?) where id = ?")) {
            statement.setInt(1, table.getCapacity());
            statement.setBoolean(2, table.isAvailable());
            statement.setInt(3, table.getNumber());
            statement.setInt(4, table.getId());
        } catch (SQLException e) {
            System.out.println("Logger updateTable(Table table)");
        }
    }

    public void deleteTable(Table table) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from tables where id = ?")) {
            statement.setInt(1, table.getId());
        } catch (SQLException e) {
            System.out.println("Logger deleteTable(Table table)");
        }
    }
}
