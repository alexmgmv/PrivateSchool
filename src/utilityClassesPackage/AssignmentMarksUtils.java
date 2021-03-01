package utilityClassesPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Scanner;
import mainPackage.IPBMain;

public class AssignmentMarksUtils {

    public static void giveAssignments() {
        IPBMain.executeUpdate("INSERT INTO `bootcampdb`.`assignmentsmarks` (`eid`, `caid`) \n"
                + "SELECT `enrollments`.`id`, `coursesassignments`.`id`\n"
                + "FROM `enrollments`, `coursesassignments`\n"
                + "WHERE `enrollments`.`cid` = `coursesassignments`.`cid`;");
    }

    public static void printAssignmentsPerStudentPerCourse() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Assignments per Course per Student");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s %-30s %-30s %-30s %-30s", "First Name", "Last Name", "Course", "Assignment", "Oral Mark", "Total Mark");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ResultSet resultSet = IPBMain.executeQuery("SELECT `students`.`fname`, `students`.`lname`, `courses`.`title`, `assignments`.`title`, `assignmentsmarks`.`omark`, `assignmentsmarks`.`tmark` FROM `assignmentsmarks`\n"
                + "INNER JOIN `enrollments` ON `enrollments`.`id` = `assignmentsmarks`.`eid`\n"
                + "INNER JOIN `coursesassignments` ON `coursesassignments`.`id` = `assignmentsmarks`.`caid`\n"
                + "INNER JOIN `students` ON `students`.`id` = `enrollments`.`sid`\n"
                + "INNER JOIN `courses` ON `courses`.`id` = `enrollments`.`cid`\n"
                + "INNER JOIN `assignments` ON `assignments`.`id` = `coursesassignments`.`aid`\n"
                + "ORDER BY `students`.`lname` ASC, `students`.`fname` ASC, `courses`.`title` ASC, `assignments`.`title` ASC;");
        boolean rs = IPBMain.printResultSet(resultSet);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void gradeAssignments(BufferedReader input, Scanner sc) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Ready to grade assignments!");
        System.out.print("Student last name: ");
        String last_name = null;
        try {
            last_name = input.readLine();
        } catch (IOException ex) {
        }
        System.out.print("Student first name: ");
        String first_name = null;
        try {
            first_name = input.readLine();
        } catch (IOException ex) {
        }
        System.out.print("Course title: ");
        String course_title = null;
        try {
            course_title = input.readLine();
        } catch (IOException ex) {
        }
        System.out.print("Assignment title: ");
        String assignment_title = null;
        try {
            assignment_title = input.readLine();
        } catch (IOException ex) {
        }
        System.out.print("Oral mark: ");
        int oral_mark = 0;
        oral_mark = sc.nextInt();
        System.out.print("Total mark: ");
        int total_mark = 0;
        total_mark = sc.nextInt();
        IPBMain.executeUpdate("UPDATE `bootcampdb`.`assignmentsmarks`\n"
                + "INNER JOIN `enrollments` ON `enrollments`.`id` = `assignmentsmarks`.`eid`\n"
                + "INNER JOIN `coursesassignments` ON `coursesassignments`.`id` = `assignmentsmarks`.`caid`\n"
                + "INNER JOIN `students` ON `students`.`id` = `enrollments`.`sid`\n"
                + "INNER JOIN `courses` ON `courses`.`id` = `enrollments`.`cid`\n"
                + "INNER JOIN `assignments` ON `assignments`.`id` = `coursesassignments`.`aid`\n"
                + "SET `assignmentsmarks`.`omark` = '" + oral_mark + "', `assignmentsmarks`.`tmark` = '" + total_mark + "'\n"
                + "WHERE (`students`.`lname` = '" + last_name + "' AND `students`.`fname` = '" + first_name + "' AND `assignments`.`title` = '" + assignment_title + "' AND `courses`.`title` = '" + course_title + "');");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
