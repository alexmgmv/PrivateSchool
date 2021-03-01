package utilityClassesPackage;

import classesPackage.Project;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Scanner;
import mainPackage.IPBMain;

public class ProjectUtils {

    public static void printProjects() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("List of all Projects");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s %-30s %-30s", "Title", "Description", "Maximum Oral Mark", "Maximum total Mark");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ResultSet resultSet = IPBMain.executeQuery("SELECT `title`, `descr`, `omark`, `tmark` FROM `bootcampdb`.`projects`;");
        boolean rs = IPBMain.printResultSet(resultSet);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void createProject(BufferedReader input, Scanner sc, int numberOfProjects) {
        if (numberOfProjects > 0) {
            for (int i = 1; i <= numberOfProjects; i++) {
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("Project " + i + " | TITLE: ");
                String title = null;
                try {
                    title = input.readLine();
                } catch (IOException ex) {
                }
                System.out.print("Project " + i + " | DESCRIPTION: ");
                String description = null;
                try {
                    description = input.readLine();
                } catch (IOException ex) {
                }
                System.out.print("Project " + i + " | MAXIMUM ORAL MARK: ");
                int omark = sc.nextInt();
                System.out.print("Project " + i + " | MAXIMUM TOTAL MARK: ");
                int tmark = sc.nextInt();
                Project p = new Project(title, description, omark, tmark);
                IPBMain.executeUpdate("INSERT INTO `bootcampdb`.`projects` (`title`, `descr`, `omark`, `tmark`) "
                        + "VALUES ('" + p.getTitle() + "', '" + p.getDescription() + "', '" + p.getMaxOralMark() + "', '" + p.getMaxTotalMark() + "');");
                CoursesProjectsUtils.relateProjects(input, sc, title);
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

}
