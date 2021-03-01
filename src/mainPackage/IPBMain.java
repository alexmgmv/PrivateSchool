package mainPackage;

import toolsPackage.Database;
import utilityClassesPackage.AssignmentMarksUtils;
import utilityClassesPackage.CourseUtils;
import utilityClassesPackage.CoursesAssignmentsUtils;
import utilityClassesPackage.CoursesProjectsUtils;
import utilityClassesPackage.EmploymentUtils;
import utilityClassesPackage.EnrollmentUtils;
import utilityClassesPackage.ProjectMarksUtils;
import utilityClassesPackage.ProjectUtils;
import utilityClassesPackage.StudentUtils;
import utilityClassesPackage.TrainerUtils;
import utilityClassesPackage.AssignmentUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class IPBMain {

    private String serverIP = "127.0.0.1";
    private String srvPort = "3306";
    private String databaseName = "bootcampdb";
    private String username = "root";
    private String password = "1234";

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData rsmd = null;

        userAction(input, sc);

    }

    /*
    - close connections! (where??? I NEED HELP)
    - check inputs!
    - check if assignment date is within class dates!
    - fix add assignment before course bug!
    - wrong date format exception
    - action for "not found"!
     */
    
    public static ResultSet executeQuery(String sql) {
        IPBMain dbProject = new IPBMain();
        ResultSet rs = null;
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        rs = db.connectAndExecuteQuery(sql);
        return (rs);
    }

    public static int executeUpdate(String sql) {
        IPBMain dbProject = new IPBMain();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate(sql);
        return (count);
    }

    public static boolean printResultSet(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = null;
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.printf("%-30s", rs.getString(i));
                }
                System.out.println();
            }
            System.out.println("\n");
            return (true);
        } catch (SQLException ex) {
            return (false);
        }
    }

    public static int questionAdd(Scanner sc, String var) {
        int answer;
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("How many " + var + " would you like to add? ");
        if (sc.hasNextInt()) {
            answer = sc.nextInt();
            return answer;
        }
        while (!sc.hasNextInt()) {
            String input = sc.next();
            System.out.print("Invalid input. Please type a number! ");
            if (sc.hasNextInt()) {
                answer = sc.nextInt();
                return answer;
            }
        }
        return 0;
    }

    public static void userAction(BufferedReader input, Scanner sc) {
        int choice;
        String answer;
        System.out.print("View a list of all \n(1) students \n(2) trainers \n(3) assignments \n(4) projects \n(5) courses \n(6) students per course"
                + "\n(7) trainers per course \n(8) assignments per course \n(9) projects per course \n(10) assignments grades \n(11) projects grades \n(12) students enrolled in multiple courses "
                + "\n Add \n(13) students \n(14) trainers \n(15) assignments \n(16) projects \n(17) courses \n Other actions \n(18) enroll students on courses"
                + "\n(19) assign trainers to courses \n(20) grade assignments \n(21) grade projects \n(Yes or No)? ");
        while (sc.hasNextInt()) {
            System.out.print("Invalid input. Please type (Yes or No)! ");
            int wrong_answer = sc.nextInt();
        }
        answer = sc.next();
        while (!(answer.toLowerCase().charAt(0) == 'y' || answer.toLowerCase().charAt(0) == 'n')) {
            System.out.print("Invalid input. Please type (Yes or No)! ");
            answer = sc.next();
        }
        while (answer.toLowerCase().charAt(0) == 'y') {
            System.out.print("Please type the associated number! ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Please type a number between 1-23! ");
                String wrong_answer = sc.next();
            }
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                boolean rs;
                switch (choice) {
                    case 1:
                        StudentUtils.printStudents();
                        break;
                    case 2:
                        TrainerUtils.printTrainers();
                        break;
                    case 3:
                        AssignmentUtils.printAssignments();
                        break;
                    case 4:
                        ProjectUtils.printProjects();
                        break;
                    case 5:
                        CourseUtils.printCourses();
                        break;
                    case 6:
                        EnrollmentUtils.printStudentsPerCourse();
                        break;
                    case 7:
                        EmploymentUtils.printTrainersPerCourse();
                        break;
                    case 8:
                        CoursesAssignmentsUtils.printAssignmentsPerCourse();
                        break;
                    case 9:
                        CoursesProjectsUtils.printProjectsPerCourse();
                        break;
                    case 10:
                        AssignmentMarksUtils.giveAssignments();
                        AssignmentMarksUtils.printAssignmentsPerStudentPerCourse();
                        break;
                    case 11:
                        ProjectMarksUtils.giveProjects();
                        ProjectMarksUtils.printProjectsPerStudentPerCourse();
                        break;
                    case 12:
                        EnrollmentUtils.printMultiCourseStudents();
                        break;
                    case 13:
                        StudentUtils.createStudent(input, sc, questionAdd(sc, "students"));
                        break;
                    case 14:
                        TrainerUtils.createTrainer(input, questionAdd(sc, "trainers"));
                        break;
                    case 15:
                        AssignmentUtils.createAssignment(input, sc, questionAdd(sc, "assignments"));
                        break;
                    case 16:
                        ProjectUtils.createProject(input, sc, questionAdd(sc, "projects"));
                        break;
                    case 17:
                        CourseUtils.createCourse(input, sc, questionAdd(sc, "courses"));
                        break;
                    case 18:
                        EnrollmentUtils.enrollStudents(input, sc);
                        break;
                    case 19:
                        EmploymentUtils.employTrainers(input, sc);
                        break;
                    case 20:
                        AssignmentMarksUtils.giveAssignments();
                        AssignmentMarksUtils.gradeAssignments(input, sc);
                        break;
                    case 21:
                        ProjectMarksUtils.giveProjects();
                        ProjectMarksUtils.gradeProjects(input, sc);
                        break;
                    default:
                        System.out.println("Invalid input. Please type a number between 1-21!");
                        break;
                }
            } else {
                System.out.println("Invalid input!");
            }
            System.out.print("Would you like to perform another action? (Yes or No) ");
            while (sc.hasNextInt()) {
                System.out.print("Invalid input. Please type Yes or No! ");
                int wrong_answer = sc.nextInt();
            }
            answer = sc.next();
            while (!(answer.toLowerCase().charAt(0) == 'y' || answer.toLowerCase().charAt(0) == 'n')) {
                System.out.print("Invalid input. Please type (Yes or No)! ");
                answer = sc.next();
            }
        }
    }

}
