package org.example.console;

import org.example.controller.GroupController;
import org.example.controller.StudentController;
import org.example.dao.GroupDao;
import org.example.dao.GroupRepo;
import org.example.dao.StudentDao;
import org.example.dao.StudentRepo;
import org.example.dto.StudentGroupDTO;
import org.example.exception.DatabaseException;
import org.example.exception.EntityNotFoundException;
import org.example.service.GroupService;
import org.example.service.StudentService;

import static org.example.util.InputUtil.getInput;

public class Operation {
    private static final GroupDao groupDao = new GroupRepo();
    private static final GroupController groupController = new GroupController(new GroupService(groupDao));

    private static final StudentDao studentDao = new StudentRepo();
    private static final StudentController studentController = new StudentController(new StudentService(studentDao));

    public static void startOperation() {
        System.out.println("\n\t\t\t---CONSOLE---");
        while (true) {
            int choice = getInput("""
                    Please select an operation:
                    ==Group operations==
                    1.Create new Group
                    2.List groups
                    3.Update Group
                    4.Delete Group
                    
                    ==Student operations==
                    5.Create new student
                    6.List students
                    7.Update student
                    8.Delete student
                    
                    9.Find student's group
                    10.EXIT
                    """, Integer.class);
            while (choice < 1 || choice > 10) {
                choice = getInput("Please enter number between 1 and 9", Integer.class);
            }

            switch (choice) {
                case 1:
                    System.out.println("---------------");
                    groupController.createGroup();
                    System.out.println("---------------");
                    break;
                case 2:
                    System.out.println("---------------");
                    try {
                        groupController.displayGroups();
                    } catch (DatabaseException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("---------------");
                    break;
                case 3:
                    System.out.println("---------------");
                    groupController.updateGroup();
                    System.out.println("---------------");
                    break;
                case 4:
                    System.out.println("---------------");
                    try {
                        groupController.deleteGroup();
                    } catch (EntityNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("---------------");
                    break;
                case 5:
                    System.out.println("---------------");
                    studentController.createStudent();
                    System.out.println("---------------");
                    break;
                case 6:
                    System.out.println("---------------");
                    studentController.getStudents();
                    System.out.println("---------------");
                    break;
                case 7:
                    System.out.println("---------------");
                    studentController.updateStudent();
                    System.out.println("---------------");
                    break;
                case 8:
                    System.out.println("---------------");
                    try {
                        studentController.deleteStudent();
                    } catch (EntityNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("---------------");
                    break;
                case 9:
                    System.out.println("---------------");
                    System.out.println(findStudentGroup());
                    System.out.println("---------------");
                    break;
                case 10:
                    return;
            }
        }
    }

    public static StudentGroupDTO findStudentGroup() {
        StudentGroupDTO studentGroupDTO = null;
        int id = getInput("Please enter the student id to find group", Integer.class);
        for (StudentGroupDTO sg : studentController.showStudentGroups()) {
            if (sg.getId() == id) {
                studentGroupDTO = sg;
            }
        }
        return studentGroupDTO;
    }

}
