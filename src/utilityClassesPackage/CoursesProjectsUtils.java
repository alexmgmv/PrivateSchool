package utilityClassesPackage;

import java.io.BufferedReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import intermediateClassesPackage.CoursesProjects;
import mainPackage.IPBMain;

public class CoursesProjectsUtils {

    public static void printProjectsPerCourse() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Projects per Course");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s", "Project Title", "Course Title");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ResultSet resultSet = IPBMain.executeQuery("SELECT `projects`.`title`, `courses`.`title` FROM `coursesprojects`\n"
                + "INNER JOIN `courses` ON `courses`.`id` = `coursesprojects`.`cid`\n"
                + "INNER JOIN `projects` ON `projects`.`id` = `coursesprojects`.`pid`\n"
                + "ORDER BY `courses`.`title` ASC, `projects`.`title` ASC;");
        boolean rs = IPBMain.printResultSet(resultSet);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void relateProjects(BufferedReader input, Scanner sc, String project) {
        HashMap<String, Integer> projects = new HashMap();
        HashMap<String, Integer> courses = new HashMap();
        ResultSet resultSet = IPBMain.executeQuery("SELECT * FROM `bootcampdb`.`projects`;");
        int project_id = 0;
        int course_id = 0;
        try {
            while (resultSet.next()) {
                project_id = resultSet.getInt("id");
                String project_title = resultSet.getString("title");
                projects.put(project_title, project_id);
            }
        } catch (SQLException ex) {
        }
        if (projects.containsKey(project)) {
            project_id = projects.get(project);
        } else {
            System.out.println("Project not found!");
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
            System.out.print("Add project '" + project + "' to course '" + set.getKey() + "? (Yes or No) ");
            String answer = sc.next();
            if (answer.toLowerCase().charAt(0) == 'y') {
                System.out.print("Type submission date for project '" + project + "'! (yyyy-M-d) ");
                String dateFromString = sc.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate submission_date = LocalDate.parse(dateFromString, formatter);
                CoursesProjects cp = new CoursesProjects(set.getValue(), project_id, submission_date);
                IPBMain.executeUpdate("INSERT INTO `bootcampdb`.`coursesprojects` (`cid`, `pid`, `subd`) VALUES (" + cp.getCourse_id() + "," + cp.getProject_id() + ", '" + cp.getSubmission_date() + "');");
            }
        }
    }

}
