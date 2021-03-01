package utilityClassesPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import intermediateClassesPackage.Employment;
import mainPackage.IPBMain;

public class EmploymentUtils {

    public static void printTrainersPerCourse() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Trainers per Course");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s %-30s", "First Name", "Last Name", "Course Title");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ResultSet resultSet = IPBMain.executeQuery("SELECT `trainers`.`fname`, `trainers`.`lname`, `courses`.`title` FROM `employments`\n"
                + "INNER JOIN `courses` ON `courses`.`id` = `employments`.`cid`\n"
                + "INNER JOIN `trainers` ON `trainers`.`id` = `employments`.`tid`\n"
                + "ORDER BY `courses`.`title` ASC, `trainers`.`lname` ASC;");
        boolean rs = IPBMain.printResultSet(resultSet);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void employTrainers(BufferedReader input, Scanner sc) {
        HashMap<String, Integer> trainers = new HashMap();
        HashMap<String, Integer> courses = new HashMap();
        ResultSet resultSet = IPBMain.executeQuery("SELECT * FROM `bootcampdb`.`trainers`;");
        int cid = 0;
        int tid = 0;
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("fname") + " " + resultSet.getString("lname");
                trainers.put(name, id);
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
        System.out.print("Assign trainers to COURSE: ");
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
        System.out.print("How many trainers would you like to assign to course '" + course + "'? ");
        int count = sc.nextInt();
        for (int i = 1; i <= count; i++) {
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.print("Trainer number " + i + " | FIRST NAME: ");
            String first_name = sc.next();
            System.out.print("Trainer number " + i + " | LAST NAME: ");
            String last_name = sc.next();
            String name = first_name + " " + last_name;
            if (trainers.containsKey(name)) {
                tid = trainers.get(name);
            } else {
                System.out.println("Trainer not found!");
            }
            Employment e = new Employment(cid, tid);
            IPBMain.executeUpdate("INSERT INTO `bootcampdb`.`employments` (`cid`, `tid`) VALUES (" + e.getCourse_id() + "," + e.getTrainer_id() + ");");
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
