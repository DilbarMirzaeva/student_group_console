package org.example.dao;

import org.example.config.ConnectionHelper;
import org.example.entity.Group;
import org.example.exception.DatabaseException;
import org.example.exception.EntityNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupRepo implements GroupDao {
    List<Group> groups = new ArrayList<>();

    @Override
    public void createGroup(Group group) {
        try (Connection connection = ConnectionHelper.getConnection()) {
            String query = "INSERT INTO groups(name,description) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, group.getName());
            ps.setString(2, group.getDescription());
            int row = ps.executeUpdate();
            System.out.println(row + " row created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Group> displayAllGroups() throws DatabaseException {
        try (Connection connection = ConnectionHelper.getConnection()) {
            String query = "SELECT * FROM groups";
            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                groups.add(new Group(id, name, description));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Database error occurred");
        }
        return groups;
    }

    @Override
    public void updateGroup(int id, Group group) {
        try (Connection connection = ConnectionHelper.getConnection()) {
            String query = "UPDATE groups SET name=?, description=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, group.getName());
            ps.setString(2, group.getDescription());
            ps.setInt(3, id);

            int rowsFind = ps.executeUpdate();
            if (rowsFind == 0) {
                throw new EntityNotFoundException("Group not found with id=" + id);
            }

            System.out.println("Group with id=" + id + " updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteGroup(int id) {
        try (Connection connection = ConnectionHelper.getConnection()) {
            String query = "DELETE FROM groups WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            int rowsDelete = ps.executeUpdate();
            if (rowsDelete == 0) {
                throw new EntityNotFoundException("Group not found with id=" + id);
            }
            System.out.println("Group with id=" + id + " deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}