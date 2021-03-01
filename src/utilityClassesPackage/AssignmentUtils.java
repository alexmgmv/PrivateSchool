package utilityClassesPackage;

import classesPackage.Assignment;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Scanner;
import mainPackage.IPBMain;

public class AssignmentUtils {

    public static void printAssignments() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("List of all Assignments");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s %-30s %-30s", "Title", "Description", "Maximum Oral Mark", "Maximum total Mark");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ResultSet resultSet = IPBMain.executeQuery("SELECT `title`, `descr`, `omark`, `tmark` FROM `bootcampdb`.`assignments`;");
        boolean rs = IPBMain.printResultSet(resultSet);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void createAssignment(BufferedReader input, Scanner sc, int numberOfAssignments) {
        if (numberOfAssignments > 0) {
            for (int i = 1; i <= numberOfAssignments; i++) {
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("Assignment " + i + " | TITLE: ");
                String title = null;
                try {
                    title = input.readLine();
                } catch (IOException ex) {
                }
                System.out.print("Assignment " + i + " | DESCRIPTION: ");
                String description = null;
                try {
                    description = input.readLine();
                } catch (IOException ex) {
                }
                System.out.print("Assignment " + i + " | MAXIMUM ORAL MARK: ");
                int omark = sc.nextInt();
                System.out.print("Assignment " + i + " | MAXIMUM TOTAL MARK: ");
                int tmark = sc.nextInt();
                Assignment a = new Assignment(title, description, omark, tmark);
                IPBMain.executeUpdate("INSERT INTO `bootcampdb`.`assignments` (`title`, `descr`, `omark`, `tmark`) "
                        + "VALUES ('" + a.getTitle() + "', '" + a.getDescription() + "', '" + a.getMaxOralMark() + "', '" + a.getMaxTotalMark() + "');");
                CoursesAssignmentsUtils.relateAssignments(input, sc, title);
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

}
