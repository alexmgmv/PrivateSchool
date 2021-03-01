package utilityClassesPackage;

import java.io.BufferedReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import intermediateClassesPackage.CoursesAssignments;
import mainPackage.IPBMain;

public class CoursesAssignmentsUtils {

    public static void printAssignmentsPerCourse() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Assignments per Course");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s", "Assignment Title", "Course Title");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ResultSet resultSet = IPBMain.executeQuery("SELECT `assignments`.`title`, `courses`.`title` FROM `coursesassignments`\n"
                + "INNER JOIN `courses` ON `courses`.`id` = `coursesassignments`.`cid`\n"
                + "INNER JOIN `assignments` ON `assignments`.`id` = `coursesassignments`.`aid`\n"
                + "ORDER BY `courses`.`title` ASC, `assignments`.`title` ASC;");
        boolean rs = IPBMain.printResultSet(resultSet);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void relateAssignments(BufferedReader input, Scanner sc, String assignment) {
        HashMap<String, Integer> assignments = new HashMap();
        HashMap<String, Integer> courses = new HashMap();
        ResultSet resultSet = IPBMain.executeQuery("SELECT * FROM `bootcampdb`.`assignments`;");
        int assignment_id = 0;
        int course_id = 0;
        try {
            while (resultSet.next()) {
                assignment_id = resultSet.getInt("id");
                String assignment_title = resultSet.getString("title");
                assignments.put(assignment_title, assignment_id);
            }
        } catch (SQLException ex) {
        }
        if (assignments.containsKey(assignment)) {
            assignment_id = assignments.get(assignment);
        } else {
            System.out.println("Assignment not found!");
        }
        resultSet = IPBMain.executeQuery("SELECT * FROM `bootcampdb`.`courses`;");
        try {
            while (resultSet.next()) {
                course_id = resultSet.getInt("id");
                String course_title = resultSet.getString("title");
                courses.put(course_title, course_id);
            }
        } catch (SQLException ex) {
        }
        for (HashMap.Entry<String, Integer> set : courses.entrySet()) {
            System.out.print("Add assignment '" + assignment + "' to course '" + set.getKey() + "? (Yes or No) ");
            String answer = sc.next();
            if (answer.toLowerCase().charAt(0) == 'y') {
                System.out.print("Type submission date for assignment '" + assignment + "'! (yyyy-M-d) ");
                String dateFromString = sc.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate submission_date = LocalDate.parse(dateFromString, formatter);
                CoursesAssignments ca = new CoursesAssignments(set.getValue(), assignment_id, submission_date);
                IPBMain.executeUpdate("INSERT INTO `bootcampdb`.`coursesassignments` (`cid`, `aid`, `subd`) VALUES (" + ca.getCourse_id() + "," + ca.getAssignment_id() + ", '" + ca.getSubmission_date() + "');");
            }
        }
    }

}
