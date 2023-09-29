package repositories;

import database.DataSource;
import models.Reservation;
import models.Table;

import java.sql.*;
import java.util.ArrayList;

import static database.SqlRequests.*;

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

    public ArrayList<Table> getFreeTables(String reservationsStart, String reservationEnd) {
        ArrayList<Table> tables = new ArrayList<>();

        try(Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(FREE_TABLES)) {
            statement.setTimestamp(1, Timestamp.valueOf(reservationsStart));
            statement.setTimestamp(2, Timestamp.valueOf(reservationsStart));
            statement.setTimestamp(3, Timestamp.valueOf(reservationEnd));
            statement.setTimestamp(4, Timestamp.valueOf(reservationEnd));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Table table = new Table(resultSet.getInt("id"),
                        resultSet.getInt("capacity"),
                        resultSet.getBoolean("is_available"),
                        resultSet.getInt("number"));
                tables.add(table);
            }
        } catch (SQLException e) {
            System.out.println("Logger getFreeTables(String reservationStart, String reservationEnd)");
        }
        return tables;
    }

    public void updateTableStatus(Reservation reservation) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_TABLE_STATUS)) {

            statement.setTimestamp(1, Timestamp.valueOf(reservation.getReservationDateStart()));
            statement.setTimestamp(2, Timestamp.valueOf(reservation.getReservationDateEnd()));
            statement.setInt(3, reservation.getTable().getId());
            statement.setInt(4, reservation.getGuest().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Logger updateTablesStatus(Reservation reservation)");
        }
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


//select tables.number from reservations inner join tables on reservations.table_id = tables.id
//        where (reservations.reservation_date_start <= '2023-09-28 22:00:00'
//        and reservations.reservation_date_end >= '2023-09-28 22:00:00')
//        or (reservations.reservation_date_start <= '2023-09-29 00:00:00'
//        and reservations.reservation_date_end >= '2023-09-29 00:00:00')