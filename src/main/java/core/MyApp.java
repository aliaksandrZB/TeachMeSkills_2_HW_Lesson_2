package core;

import java.sql.*;
import java.util.Scanner;

public class MyApp {

    public static void main(String[] args) {

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teachmeskills_2_hw_lesson_2", "admin", "admin");

            printStudentData(connection);

            changeCity(connection);
            addedStudent(connection);
            removingStudent(connection);

            printStudentData(connection);

            connection.close();
        } catch (SQLException e) {
            System.out.println("Unable to load driver class.");
            e.printStackTrace();
        }

    }

    private static void printStudentData(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from student");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + ". " +
                    rs.getString("name") + " " +
                    rs.getString("last_name") + ", " +
                    rs.getString("city") + ".");
        }
        rs.close();
    }

    private static void changeCity(Connection connection) {
        System.out.println("Enter the name and last name of student whose city you want to change" +
                           ", as well as the name of the city.\nName:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Last name:");
        String lastName = scanner.nextLine();
        System.out.println("City name:");
        String cityName = scanner.nextLine();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE student SET city = \"" + cityName +
                                    "\" WHERE name = \"" + name + "\" AND last_name = \"" +
                                    lastName + "\";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addedStudent(Connection connection) throws SQLException {
        System.out.println("Enter new students details\nName:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Last name:");
        String lastName = scanner.nextLine();
        System.out.println("City name:");
        String cityName = scanner.nextLine();

        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO student (name, last_name, city) VALUES (\"" + name +
                          "\", \"" + lastName + "\", \"" + cityName + "\");");

    }

    private static void removingStudent(Connection connection) throws SQLException {
        System.out.println("Enter the first and last name of the student to be deleted\nName:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Last name:");
        String lastName = scanner.nextLine();

        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM student WHERE name = \"" + name +
                                "\" AND last_name = \"" + lastName + "\";");
    }


}
