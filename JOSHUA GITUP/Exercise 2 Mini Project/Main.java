/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classroom;

/**
 *
 * @author JOSHUA S
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Classroom {
    private String name;
    private List<Student> students;
    private List<Assignment> assignments;

    public Classroom(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }
}

class Student {
    private String id;

    public Student(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

class Assignment {
    private String details;

    public Assignment(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}

class VirtualClassroomManager {
    private Map<String, Classroom> classrooms;

    public VirtualClassroomManager() {
        classrooms = new HashMap<>();
    }

    public void addClassroom(String className) {
        classrooms.put(className, new Classroom(className));
        System.out.println("Classroom " + className + " has been created.");
    }

    public void addStudent(String studentId, String className) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            classroom.addStudent(new Student(studentId));
            System.out.println("Student " + studentId + " has been enrolled in " + className + ".");
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }

    public void scheduleAssignment(String className, String assignmentDetails) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            classroom.addAssignment(new Assignment(assignmentDetails));
            System.out.println("Assignment for " + className + " has been scheduled.");
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }

    public void submitAssignment(String studentId, String className, String assignmentDetails) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            boolean studentExists = classroom.getStudents().stream().anyMatch(student -> student.getId().equals(studentId));
            if (studentExists) {
                System.out.println("Assignment submitted by Student " + studentId + " in " + className + ".");
            } else {
                System.out.println("Student " + studentId + " is not enrolled in " + className + ".");
            }
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        VirtualClassroomManager manager = new VirtualClassroomManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            switch (command) {
                case "add_classroom":
                    manager.addClassroom(parts[1]);
                    break;
                case "add_student":
                    String[] studentInfo = parts[1].split(" ", 2);
                    manager.addStudent(studentInfo[0], studentInfo[1]);
                    break;
                case "schedule_assignment":
                    String[] assignmentInfo = parts[1].split(" ", 2);
                    manager.scheduleAssignment(assignmentInfo[0], assignmentInfo[1]);
                    break;
                case "submit_assignment":
                    String[] submissionInfo = parts[1].split(" ", 3);
                    manager.submitAssignment(submissionInfo[0], submissionInfo[1], submissionInfo[2]);
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}
