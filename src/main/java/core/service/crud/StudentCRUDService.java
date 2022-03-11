package core.service.crud;

import core.service.MyWriter;
import core.service.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentCRUDService extends CRUDService {

    private static final String SQL_SAMPLE_FOR_ALL = "SELECT * FROM students";
    private static final String SQL_ADDED_NEW_STUDENTS = "INSERT INTO students (name, last_name) VALUES (?, ?);";
    private static final String SQL_REMOVING_STUDENT = "DELETE FROM students WHERE name = ? AND last_name = ?;";
    private static final String SQL_UPDATE_COUNTER_OF_STUDENTS_FIRST = "ALTER TABLE students MODIFY COLUMN id INT UNSIGNED;";
    private static final String SQL_UPDATE_COUNTER_OF_STUDENTS_SECOND = "ALTER TABLE students MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT;";

    public StudentCRUDService(MySQLConnector connector) {
        setConnection(connector.getConnection());
        scanner = new Scanner(System.in);
    }

    public void printListOfStudent() {
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SAMPLE_FOR_ALL);

            while (rs.next()) {
                MyWriter.println(rs.getInt("id") + ". " +
                               rs.getString("name") + " " +
                               rs.getString("last_name") + ".");
            }
            MyWriter.separator();

            rs.close();
        } catch (SQLException e) {
            MyWriter.println("failed request");
            e.printStackTrace();
        }

    }

    public void addedNewStudent() {
        MyWriter.println("Enter new students data.");

        completeQueryWithInputOfFirstAndLastName(SQL_ADDED_NEW_STUDENTS);

        MyWriter.println("Student successfully added.");
        MyWriter.separator();
    }

    public void removingStudent() {
        MyWriter.println("Enter the first and last name of the student to be deleted.");

        completeQueryWithInputOfFirstAndLastName(SQL_REMOVING_STUDENT);

        MyWriter.println("Student successfully deleted.");
        MyWriter.separator();
    }

    public void updateCounterOfStudents() {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(SQL_UPDATE_COUNTER_OF_STUDENTS_FIRST);
            statement.executeUpdate(SQL_UPDATE_COUNTER_OF_STUDENTS_SECOND);
        } catch (SQLException e) {
            MyWriter.println("failed request");
            e.printStackTrace();
        }
    }


    public void completeQueryWithInputOfFirstAndLastName(String sqlQuery) {
        MyWriter.println("Name:");
        String name = scanner.nextLine();
        MyWriter.println("Last name:");
        String lastName = scanner.nextLine();

        try (PreparedStatement prepStatement = getConnection().prepareStatement(sqlQuery)) {
            prepStatement.setString(1, name);
            prepStatement.setString(2, lastName);
            prepStatement.execute();
        } catch (SQLException e) {
            MyWriter.println("failed request");
            e.printStackTrace();
        }
    }


}
