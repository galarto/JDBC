package repositories;

import database.DataSource;
import models.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public Table getTable(int number) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from tables where number = ?")) {
            statement.setInt(1, number);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return new Table(resultSet.getInt("id"),
                        resultSet.getInt("capacity"),
                        resultSet.getBoolean("is_available"),
                        resultSet.getInt("number"));
            }
        } catch (SQLException e) {
            System.out.println("Logger getTable(int number)");
        }
        return null;
    }

    public void updateTable(Table table) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("update tables set capacity = ?, " +
                "is_available = ?, number = ? where id = ?")) {
            statement.setInt(1, table.getCapacity());
            statement.setBoolean(2, table.isAvailable());
            statement.setInt(3, table.getNumber());
            statement.setInt(4, table.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Logger updateTable(Table table)");
        }
    }

    public void deleteTable(int number) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from tables where number = ?")) {
            statement.setInt(1, number);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Logger deleteTable(int number)");
        }
    }

    public ArrayList<Table> getTables() {
        return new ArrayList<>();
    }
}



//update tables
//set is_available = false
//from reservations r
//inner join tables t
//on r.table_id = t.id
//inner join guests g
//on r.guest_id = g.id
//where reservation_date_start = ?
//and reservation_date_end = ?
//and t.id = ?
//and g.id = ?
