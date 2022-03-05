package core.writer_reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class WriterReader {

    public static void printDataOfStudent(ResultSet rs) {
        try {
            System.out.println(rs.getInt("id") + ". " +
                    rs.getString("name") + " " +
                    rs.getString("last_name") + ".");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printDataOfStudentAndHisCity(ResultSet rs) {
        try {
            System.out.println(rs.getInt("id") + ". " +
                    rs.getString("name") + " " +
                    rs.getString("last_name") + ", " +
                    rs.getString("city")+ ".");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String enterCityName() {
        System.out.println("Enter city name:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String enterStudentName() {
        System.out.println("Name:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String enterStudentLastName() {
        System.out.println("Last name:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void writeAddStudent() {
        System.out.println("Enter new students data.");
    }

    public static void writeAboutSuccessfulAdditionOfStudent() {
        System.out.println("Student successfully added.");
        separator();
    }

    public static void writeAboutSuccessfulAdditionOfCity() {
        System.out.println("City successfully added.");
        separator();
    }

    public static void writeAboutSuccessfulRemovalOfCity() {
        System.out.println("City successfully deleted.");
        separator();
    }

    public static void writeAboutSuccessfulRemovalOfStudent() {
        System.out.println("Student successfully deleted.");
        separator();
    }

    public static void writeAboutSuccessfulUpdateOfCounter() {
        System.out.println("Counter successfully update.");
        separator();
    }

    public static void writeRemovingStudent() {
        System.out.println("Enter the first and last name of the student to be deleted.");
    }

    public static void callMenu() {
        System.out.println("1 - Display student data.");
        System.out.println("2 - Display data of students and their cities.");
        System.out.println("3 - Add a student.");
        System.out.println("4 - Add city.");
        System.out.println("5 - Delete a student.");
        System.out.println("6 - Delete city.");
        System.out.println("7 - Update the counter of students and cities.");
        System.out.println("0 - Complete the program.");
        System.out.println("\nYour choice: ");
    }

    public static void writeWrongChoice() {
        System.out.println("Wrong choice, try again");
        separator();
    }

    public static void separator() {
        System.out.println("\n----------------------------\n");
    }
}
