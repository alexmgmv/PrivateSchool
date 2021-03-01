package utilityClassesPackage;

import classesPackage.Course;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import mainPackage.IPBMain;

public class CourseUtils {

    public static void printCourses() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("List of all Courses");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s %-30s %-30s %-30s", "Title", "Stream", "Type", "Start Date", "End Date");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ResultSet resultSet = IPBMain.executeQuery("SELECT  `title`, `stream`, `type`, `sdate`, `edate` FROM `bootcampdb`.`courses`; ");
        boolean rs = IPBMain.printResultSet(resultSet);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void createCourse(BufferedReader input, Scanner sc, int numberOfCourses) {
        if (numberOfCourses > 0) {
            for (int i = 1; i <= numberOfCourses; i++) {
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("Course " + i + " | TITLE: ");
                String title = null;
                try {
                    title = input.readLine();
                } catch (IOException ex) {
                }
                System.out.print("Course " + i + " | STREAM: ");
                String stream = null;
                try {
                    stream = input.readLine();
                } catch (IOException ex) {
                }
                System.out.print("Course " + i + " | TYPE: ");
                String type = null;
                try {
                    type = input.readLine();
                } catch (IOException ex) {
                }
                System.out.print("Course " + i + " | START DATE: (yyyy-M-d) ");
                String dateFromString = sc.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate start_date = LocalDate.parse(dateFromString, formatter);
                System.out.print("Course " + i + " | END DATE: (yyyy-M-d) ");
                dateFromString = sc.next();
                LocalDate end_date = LocalDate.parse(dateFromString, formatter);
                Course c = new Course(title, stream, type, start_date, end_date);
                IPBMain.executeUpdate("INSERT INTO `bootcampdb`.`courses` (`title`, `stream`, `type`, `sdate`, `edate`) "
                        + "VALUES ('" + c.getTitle() + "', '" + c.getStream() + "', '" + c.getType() + "', '" + c.getStartDate() + "', '" + c.getEndDate() + "');");
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

}
