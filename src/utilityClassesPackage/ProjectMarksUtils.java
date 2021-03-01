package utilityClassesPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Scanner;
import mainPackage.IPBMain;

public class ProjectMarksUtils {

    public static void giveProjects() {
        IPBMain.executeUpdate("INSERT INTO `bootcampdb`.`projectsmarks` (`eid`, `cpid`) \n"
                + "SELECT `enrollments`.`id`, `coursesprojects`.`id`\n"
                + "FROM `enrollments`, `coursesprojects`\n"
                + "WHERE `enrollments`.`cid` = `coursesprojects`.`cid`;");
    }

    public static void printProjectsPerStudentPerCourse() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Projects per Course per Student");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s %-30s %-30s %-30s %-30s", "First Name", "Last Name", "Course", "Project", "Oral Mark", "Total Mark");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ResultSet resultSet = IPBMain.executeQuery("SELECT `students`.`fname`, `students`.`lname`, `courses`.`title`, `projects`.`title`, `projectsmarks`.`omark`, `projectsmarks`.`tmark` FROM `projectsmarks`\n"
                + "INNER JOIN `enrollments` ON `enrollments`.`id` = `projectsmarks`.`eid`\n"
                + "INNER JOIN `coursesprojects` ON `coursesprojects`.`id` = `projectsmarks`.`cpid`\n"
                + "INNER JOIN `students` ON `students`.`id` = `enrollments`.`sid`\n"
                + "INNER JOIN `courses` ON `courses`.`id` = `enrollments`.`cid`\n"
                + "INNER JOIN `projects` ON `projects`.`id` = `coursesprojects`.`pid`\n"
                + "ORDER BY `students`.`lname` ASC, `students`.`fname` ASC, `courses`.`title` ASC, `projects`.`title` ASC;");
        boolean rs = IPBMain.printResultSet(resultSet);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void gradeProjects(BufferedReader input, Scanner sc) {
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
        System.out.print("Project title: ");
        String project_title = null;
        try {
            project_title = input.readLine();
        } catch (IOException ex) {
        }
        System.out.print("Oral mark: ");
        int oral_mark = 0;
        oral_mark = sc.nextInt();
        System.out.print("Total mark: ");
        int total_mark = 0;
        total_mark = sc.nextInt();
        IPBMain.executeUpdate("UPDATE `bootcampdb`.`projectsmarks`\n"
                + "INNER JOIN `enrollments` ON `enrollments`.`id` = `projectsmarks`.`eid`\n"
                + "INNER JOIN `coursesprojects` ON `coursesprojects`.`id` = `projectsmarks`.`cpid`\n"
                + "INNER JOIN `students` ON `students`.`id` = `enrollments`.`sid`\n"
                + "INNER JOIN `courses` ON `courses`.`id` = `enrollments`.`cid`\n"
                + "INNER JOIN `projects` ON `projects`.`id` = `coursesprojects`.`pid`"
                + "SET `projectsmarks`.`omark` = '" + oral_mark + "', `projectsmarks`.`tmark` = '" + total_mark + "'\n"
                + "WHERE (`students`.`lname` = '" + last_name + "' AND `students`.`fname` = '" + first_name + "' AND `projects`.`title` = '" + project_title + "' AND `courses`.`title` = '" + course_title + "');");
    }

}
