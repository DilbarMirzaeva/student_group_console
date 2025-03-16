package org.example.service;

import org.example.dao.StudentDao;
import org.example.entity.Student;

import java.util.List;

public class StudentService {
    StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void insertStudent(Student student) {
        studentDao.createStudents(student);
    }

    public List<Student> displayAllStudents() {
        return studentDao.displayAllStudents();
    }

    public void updateStudent(int id, Student student) {
        studentDao.updateStudent(id, student);
    }

    public void deleteStudent(int id) {
        studentDao.deleteStudent(id);
    }
}
