package repositories;

import database.DataSource;
import models.Guest;
import models.Reservation;
import models.Table;

import java.sql.*;
import java.time.LocalDateTime;

import static database.SqlRequests.ADD_RESERVATION;


public class ReservationRepository {
    public void add(Reservation reservation) {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(ADD_RESERVATION)) {
            statement.setTimestamp(1, Timestamp.valueOf(reservation.getReservationDateStart()));
            statement.setTimestamp(2, Timestamp.valueOf(reservation.getReservationDateEnd()));
            statement.setInt(3, reservation.getTable().getId());
            statement.setInt(4, reservation.getGuest().getId());
            statement.setInt(5, reservation.getNumberOfPersons());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Logger add(Reservation reservation)");
        }
    }

    public Reservation getReservation(Guest guest) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from reservations " +
                "inner join guests on reservations.guest_id = guests.id " +
                "inner join tables on reservations.table_id = tables.id " +
                "where guest_id = ? and reservation_date_start > ?")) {
            statement.setInt(1, guest.getId());
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return new Reservation(resultSet.getInt("id"),
                        resultSet.getTimestamp("reservation_date_start").toLocalDateTime(),
                        resultSet.getInt("number_of_persons"),
                        new Table(resultSet.getInt("table_id"), resultSet.getInt("capacity"),
                                resultSet.getInt("number")),
                        new Guest(resultSet.getInt("guest_id"), resultSet.getString("name"),
                                resultSet.getString("surname"), resultSet.getString("phone_number")));
            }
        } catch (SQLException e) {
            System.out.println("Logger getReservation(Reservation reservation)");
        }
        return null;
    }

    public void updateReservation(Reservation reservation) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("update reservations " +
                "set(reservation_date_start = ?, reservation_date_end = ?, table_id = ?, guest_id = ?, " +
                "number_of_persons = ?) where id = ?")) {
            statement.setTimestamp(1, Timestamp.valueOf(reservation.getReservationDateStart()));
            statement.setTimestamp(2, Timestamp.valueOf(reservation.getReservationDateEnd()));
            statement.setInt(3, reservation.getTable().getId());
            statement.setInt(4, reservation.getGuest().getId());
            statement.setInt(5, reservation.getNumberOfPersons());
            statement.setInt(6, reservation.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Logger updateReservation(Reservation reservation)");
        }
    }

    public void deleteReservation(Reservation reservation) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from reservations where id = ?")) {
            statement.setInt(1, reservation.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Logger deleteReservation(Reservation reservation)");
        }
    }
}
