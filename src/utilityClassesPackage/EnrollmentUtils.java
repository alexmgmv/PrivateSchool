package utilityClassesPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import intermediateClassesPackage.Enrollment;
import mainPackage.IPBMain;

public class EnrollmentUtils {

    public static void printStudentsPerCourse() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Students per Course");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s %-30s", "First Name", "Last Name", "Course Title");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ResultSet resultSet = IPBMain.executeQuery("SELECT `students`.`fname`, `students`.`lname`, `courses`.`title` FROM `enrollments`\n"
                + "INNER JOIN `courses` ON `courses`.`id` = `enrollments`.`cid`\n"
                + "INNER JOIN `students` ON `students`.`id` = `enrollments`.`sid`\n"
                + "ORDER BY `courses`.`title` ASC, `students`.`lname` ASC;");
        boolean rs = IPBMain.printResultSet(resultSet);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void printMultiCourseStudents() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Students enrolled in more than one Course");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s", "First Name", "Last Name");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ResultSet resultSet = IPBMain.executeQuery("SELECT `students`.`fname`, `students`.`lname` FROM `enrollments`\n"
                + "INNER JOIN `courses` ON `courses`.`id` = `enrollments`.`cid`\n"
                + "INNER JOIN `students` ON `students`.`id` = `enrollments`.`sid`\n"
                + "GROUP BY `enrollments`.`sid`\n"
                + "HAVING COUNT(`enrollments`.`sid`) > 1\n"
                + "ORDER BY `students`.`lname`;");
        boolean rs = IPBMain.printResultSet(resultSet);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void enrollStudents(BufferedReader input, Scanner sc) {
        HashMap<String, Integer> students = new HashMap();
        HashMap<String, Integer> courses = new HashMap();
        ResultSet resultSet = IPBMain.executeQuery("SELECT * FROM `bootcampdb`.`students`;");
        int cid = 0;
        int sid = 0;
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("fname") + " " + resultSet.getString("lname");
                students.put(name, id);
            }
        } catch (SQLException ex) {
        }
        resultSet = IPBMain.executeQuery("SELECT * FROM `bootcampdb`.`courses`;");
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                courses.put(title, id);
            }
        } catch (SQLException ex) {
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("Enroll students in COURSE: ");
        String course = null;
        try {
            course = input.readLine();
        } catch (IOException ex) {
        }
        if (courses.containsKey(course)) {
            cid = courses.get(course);
        } else {
            System.out.println("Course not found!");
        }
        System.out.print("How many students would you like to enroll in course '" + course + "'? ");
        int count = sc.nextInt();
        for (int i = 1; i <= count; i++) {
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.print("Student number " + i + " | FIRST NAME: ");
            String first_name = sc.next();
            System.out.print("Student number " + i + " | LAST NAME: ");
            String last_name = sc.next();
            String name = first_name + " " + last_name;
            if (students.containsKey(name)) {
                sid = students.get(name);
            } else {
                System.out.println("Student not found!");
            }
            Enrollment e = new Enrollment(sid, cid);
            IPBMain.executeUpdate("INSERT INTO `bootcampdb`.`enrollments` (`cid`, `sid`) VALUES (" + e.getCourse_id() + "," + e.getStudent_id() + ");");
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
