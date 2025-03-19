package org.example.controller;

import org.example.dto.StudentGroupDTO;
import org.example.entity.Student;
import org.example.service.StudentService;

import java.util.List;

import static org.example.util.InputUtil.getInput;

public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public void createStudent(){
        Student student=inputStudent();
        studentService.insertStudent(student);
    }

    public void getStudents() {
        List<Student> students= studentService.displayAllStudents();
        students.forEach(System.out::println);
    }

    public void updateStudent() {
        int id=getInput("Enter the student id to update", Integer.class);
        System.out.println("==New group information==");
        Student student=inputStudent();
        studentService.updateStudent(id, student);
    }

    public void deleteStudent(){
        int id=getInput("Enter the student id to delete", Integer.class);
        studentService.deleteStudent(id);
    }

    public Student inputStudent(){
        String name=getInput("Enter the student name: ",String.class);
        String email=getInput("Enter the student email: ",String.class);
        int group_id=getInput("Enter the student group id: ",Integer.class);
        return new Student(name,email,group_id);
    }
    
    public List<StudentGroupDTO> showStudentGroups() {
        return studentService.getStudentsWithGroups();
    }
}
