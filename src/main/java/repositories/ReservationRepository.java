package repositories;

import database.DataSource;
import models.Reservation;

import java.sql.*;
import java.time.LocalDateTime;


public class ReservationRepository {
    public void add(Reservation reservation) {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into reservations " +
                            "(reservation_date_start, reservation_date_end, table_id, guest_id, number_of_persons)" +
                    "values(?, ?, ?, ?, ?)")) {
            statement.setTimestamp(1, Timestamp.valueOf(reservation.getReservationDateStart()));
            statement.setTimestamp(2, Timestamp.valueOf(reservation.getReservationDateEnd()));
            statement.setInt(3, reservation.getTable().getId());
            statement.setInt(4, reservation.getGuest().getId());
            statement.setInt(5, reservation.getNumberOfPersons());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Logger add(Reservation reservation");
        }
    }

    public Reservation getReservation(Reservation reservation) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from reservations " +
                "where guest_id = ? and reservation_date_start = ?")) {
            statement.setInt(1, reservation.getGuest().getId());
            statement.setTimestamp(2, Timestamp.valueOf(reservation.getReservationDateStart()));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return new Reservation(resultSet.getInt("id"),
                        resultSet.getTimestamp("reservation_date_start"),
                        resultSet.getInt("number_of_persons"),
                        resultSet.getObject("guest_id"),
                        resultSet.getObject("table_id"));
            }
        } catch (SQLException e) {
            System.out.println("Logger getReservation(Reservation reservation)");
        }
        return null;
    }
}
