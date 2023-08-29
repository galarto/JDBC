package repositories;

import database.DataSource;
import models.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import static database.SqlRequests.ADD_GUEST;

public class GuestRepository {

    public void add(Guest guest) {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(ADD_GUEST)) {
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getSurname());
            statement.setString(3, guest.getPhoneNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Logger add(Guest guest)");
        }
    }

    public Guest getGuest(int id) {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from guest where id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Guest(resultSet.getInt("id"),
                                 resultSet.getString("name"),
                                 resultSet.getString("surname"),
                                 resultSet.getString("phone_number"));
            }
        } catch (SQLException e) {
            System.out.println("Logger add(Guest guest)");
        }
        return null;
    }
}
