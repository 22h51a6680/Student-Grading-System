
import java.util.Scanner;

/*
 * Student Grading System
 * 
 * Concepts Used:
 * - Classes (Student, Course, Grade)
 * - Inheritance (UndergraduateStudent, GraduateStudent)
 * - Encapsulation (Student records)
 * - Polymorphism (Different grading methods)
 */
// Class to represent a Course
class Course {

    private String courseName;
    private int courseCredits;

    public Course(String courseName, int courseCredits) {
        this.courseName = courseName;
        this.courseCredits = courseCredits;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseCredits() {
        return courseCredits;
    }
}

// Class to represent a Grade
class Grade {

    private double gradeValue;

    public Grade(double gradeValue) {
        this.gradeValue = gradeValue;
    }

    public double getGradeValue() {
        return gradeValue;
    }
}

// Base class to represent a Student
class Student {

    private String name;
    private int studentID;
    private Grade[] grades;
    private Course[] courses;

    public Student(String name, int studentID, Course[] courses) {
        this.name = name;
        this.studentID = studentID;
        this.courses = courses;
        this.grades = new Grade[courses.length];
    }

    public String getName() {
        return name;
    }

    public int getStudentID() {
        return studentID;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setGrade(int courseIndex, double gradeValue) {
        if (courseIndex >= 0 && courseIndex < grades.length) {
            grades[courseIndex] = new Grade(gradeValue);
        }
    }

    // Polymorphism: Different grading methods (overriding)
    public String calculateGrade() {
        double total = 0;
        int totalCredits = 0;

        for (int i = 0; i < courses.length; i++) {
            if (grades[i] != null) {
                total += grades[i].getGradeValue() * courses[i].getCourseCredits();
                totalCredits += courses[i].getCourseCredits();
            }
        }

        double finalGrade = total / totalCredits;
        return "Final Grade: " + String.format("%.2f", finalGrade);
    }
}

// Inheritance: Derived class for Undergraduate Students
class UndergraduateStudent extends Student {

    public UndergraduateStudent(String name, int studentID, Course[] courses) {
        super(name, studentID, courses);
    }

    @Override
    public String calculateGrade() {
        return "Undergraduate " + super.calculateGrade();
    }
}

// Inheritance: Derived class for Graduate Students
class GraduateStudent extends Student {

    public GraduateStudent(String name, int studentID, Course[] courses) {
        super(name, studentID, courses);
    }

    @Override
    public String calculateGrade() {
        return "Graduate " + super.calculateGrade();
    }
}

// Main class to demonstrate the functionality
public class StudentGradingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define courses
        Course[] courses = {
            new Course("Math", 3),
            new Course("Science", 4),
            new Course("History", 2)
        };

        // Ask the user to select student type
        System.out.println("Select Student Type: ");
        System.out.println("1. Undergraduate");
        System.out.println("2. Graduate");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Input student details
        System.out.println("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Student ID: ");
        int studentID = scanner.nextInt();

        // Create student object based on choice
        Student student;
        if (choice == 1) {
            student = new UndergraduateStudent(name, studentID, courses);
        } else if (choice == 2) {
            student = new GraduateStudent(name, studentID, courses);
        } else {
            System.out.println("Invalid choice! Exiting...");
            scanner.close();
            return;
        }

        // Input grades for the selected student
        for (int i = 0; i < courses.length; i++) {
            System.out.println("Enter grade for " + courses[i].getCourseName() + ": ");
            double grade = scanner.nextDouble();
            student.setGrade(i, grade);
        }

        // Display result
        System.out.println("\n" + student.calculateGrade());

        scanner.close();
    }
}
