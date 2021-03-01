package utilityClassesPackage;

import classesPackage.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import mainPackage.IPBMain;

public class StudentUtils {

    public static void printStudents() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("List of all Students");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s %-30s %-30s", "First Name", "Last Name", "Date of Birth", "Tuition Fees");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ResultSet resultSet = IPBMain.executeQuery("SELECT `fname`, `lname`, `dob`, `tuit` FROM `bootcampdb`.`students`;");
        boolean rs = IPBMain.printResultSet(resultSet);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    public static void createStudent(BufferedReader input, Scanner sc, int numberOfStudents) {
        if (numberOfStudents > 0) {
            for (int i = 1; i <= numberOfStudents; i++) {
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("Student " + i + " | FIRST NAME: ");
                String first_name = null;
                try {
                    first_name = input.readLine();
                } catch (IOException ex) {
                }
                System.out.print("Student " + i + " | LAST NAME: ");
                String last_name = null;
                try {
                    last_name = input.readLine();
                } catch (IOException ex) {
                }
                System.out.print("Student " + i + " | DATE OF BIRTH: (yyyy-M-d) ");
                String dateFromString = sc.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate date_of_birth = LocalDate.parse(dateFromString, formatter);
                System.out.print("Student " + i + "| TUITION FEES: ");
                int tuition_fees = sc.nextInt();
                Student st = new Student(first_name, last_name, date_of_birth, tuition_fees);
                IPBMain.executeUpdate("INSERT INTO `bootcampdb`.`students` (`fname`, `lname`, `dob`, `tuit`) "
                        + "VALUES ('" + st.getFirstName() + "', '" + st.getLastName() + "', '" + st.getDateOfBirth() + "', '" + st.getTuitionFees() + "');");
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        }
    }

}
