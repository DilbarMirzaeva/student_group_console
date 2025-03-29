package org.example.dao;

import org.example.config.ConnectionHelper;
import org.example.dto.StudentGroupDTO;
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


    @Override
    public void createStudents(Student student) {
        try (Connection connection = ConnectionHelper.getConnection()) {
            String checkQuery="SELECT * FROM groups WHERE id=?";
            PreparedStatement psCheck=connection.prepareStatement(checkQuery);
            psCheck.setInt(1,student.getGroup_id());
            ResultSet rsCheck=psCheck.executeQuery();
            if (!rsCheck.next()) {
                System.out.println("Error: Group ID " + student.getGroup_id() + " does not exist!");
                return;
            }

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
        List<Student> students = new ArrayList<>();
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
            throw new DatabaseException("Database error occurred"+e.getMessage());
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
            ps.setInt(4, id);

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
            System.out.println(e.getMessage());
        }
    }

    public List<StudentGroupDTO> getStudentsWithGroup() throws DatabaseException{
        List<StudentGroupDTO> studentGroupDTOs = new ArrayList<>();
        try(Connection connection=ConnectionHelper.getConnection()) {
            String query="SELECT s.id,s.name,s.email,g.name AS group_name " +
                    "FROM student s  JOIN groups g on s.group_id=g.id";
            PreparedStatement ps=connection.prepareStatement(query);

            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String email=resultSet.getString("email");
                String group_name=resultSet.getString("group_name");

                studentGroupDTOs.add(new StudentGroupDTO(id,name,email,group_name));
            }
        } catch (SQLException e) {
            throw  new DatabaseException("Database error occurred"+e.getMessage());
        }

        return studentGroupDTOs;
    }
}
