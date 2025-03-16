package org.example.dao;

import org.example.config.ConnectionHelper;
import org.example.entity.Student;
import org.example.exception.DatabaseException;
import org.example.exception.EntityNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo implements StudentDao {
    List<Student> students = new ArrayList<>();

    @Override
    public void createStudents(Student student) {
        try (Connection connection = ConnectionHelper.getConnection()) {
            String query = "INSERT INTO student(name,email,group_id) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getGroup_id());
            int row = ps.executeUpdate();
            System.out.println(row + " row created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Student> displayAllStudents() throws DatabaseException {
        try (Connection connection = ConnectionHelper.getConnection()) {
            String query = "SELECT * FROM student";
            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Integer group_id = resultSet.getInt("group_id");
                students.add(new Student(id, name, email, group_id));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Database error occurred");
        }
        return students;
    }

    @Override
    public void updateStudent(int id, Student student) {
        try (Connection connection = ConnectionHelper.getConnection()) {
            String query = "UPDATE student SET name=?, email=?,group_id=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getGroup_id());
            ps.setInt(3, id);

            int rowsFind = ps.executeUpdate();
            if (rowsFind == 0) {
                throw new EntityNotFoundException("Student not found with id=" + id);
            }

            System.out.println("Student with id=" + id + " updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteStudent(int id) {
        try (Connection connection = ConnectionHelper.getConnection()) {
            String query = "DELETE FROM student WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            int rowsDelete = ps.executeUpdate();
            if (rowsDelete == 0) {
                throw new EntityNotFoundException("Student not found with id=" + id);
            }
            System.out.println("Student with id=" + id + " deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
