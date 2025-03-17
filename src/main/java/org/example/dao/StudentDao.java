package org.example.dao;

import org.example.dto.StudentGroupDTO;
import org.example.entity.Group;
import org.example.entity.Student;

import javax.swing.*;
import java.util.List;

public interface StudentDao {
    void createStudents(Student student);

    List<Student> displayAllStudents();

    void updateStudent(int id, Student student);

    void deleteStudent(int id);

    List<StudentGroupDTO> getStudentsWithGroup();
}
