package utilityClassesPackage;

import classesPackage.Trainer;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import mainPackage.IPBMain;

public class TrainerUtils {

    public static void printTrainers() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("List of all Trainers");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s %-30s", "First Name", "Last Name", "Subject");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ResultSet resultSet = IPBMain.executeQuery("SELECT `fname`, `lname`, `subj` FROM `bootcampdb`.`trainers`;");
        boolean rs = IPBMain.printResultSet(resultSet);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void createTrainer(BufferedReader input, int numberOfTrainers) {
        if (numberOfTrainers > 0) {
            for (int i = 1; i <= numberOfTrainers; i++) {
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("Trainer " + i + " | FIRST NAME: ");
                String first_name = null;
                try {
                    first_name = input.readLine();
                } catch (IOException ex) {
                }
                System.out.print("Trainer " + i + " | LAST NAME: ");
                String last_name = null;
                try {
                    last_name = input.readLine();
                } catch (IOException ex) {
                }
                System.out.print("Trainer " + i + " | SUBJECT: ");
                String subject = null;
                try {
                    subject = input.readLine();
                } catch (IOException ex) {
                }
                Trainer tr = new Trainer(first_name, last_name, subject);
                IPBMain.executeUpdate("INSERT INTO `bootcampdb`.`trainers` (`fname`, `lname`, `subj`) "
                        + "VALUES ('" + tr.getFirstName() + "', '" + tr.getLastName() + "', '" + tr.getSubject() + "');");
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

}
