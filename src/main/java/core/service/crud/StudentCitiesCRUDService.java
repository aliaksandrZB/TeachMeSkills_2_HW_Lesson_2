package core.service.crud;

import core.service.MyWriter;
import core.service.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentCitiesCRUDService extends CRUDService {

    private static final String SQL_SAMPLE_FOR_STUDENTS_AND_CITIES = "SELECT students.id, name, last_name, student_cities.city FROM students INNER JOIN student_cities ON students.id = student_cities.id;";
    private static final String SQL_ADDED_NEW_CITY = "INSERT INTO student_cities (city) VALUES (?);";
    private static final String SQL_REMOVING_CITY = "DELETE FROM student_cities WHERE city = ?;";
    private static final String SQL_UPDATE_COUNTER_OF_STUDENT_CITIES_FIRST = "ALTER TABLE student_cities MODIFY COLUMN id INT UNSIGNED;";
    private static final String SQL_UPDATE_COUNTER_OF_STUDENT_CITIES_SECOND = "ALTER TABLE student_cities MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT";

    public StudentCitiesCRUDService(MySQLConnector connector) {
        setConnection(connector.getConnection());
        scanner = new Scanner(System.in);
    }

    public void printListOfStudentAndCities() {
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SAMPLE_FOR_STUDENTS_AND_CITIES);

            while (rs.next()) {
                MyWriter.println(rs.getInt("id") + ". " +
                                   rs.getString("name") + " " +
                                   rs.getString("last_name") + ", " +
                                   rs.getString("city")+ ".");
            }
            MyWriter.separator();

            rs.close();
        } catch (SQLException e) {
            MyWriter.println("failed request");
            e.printStackTrace();
        }

    }

    public void addedNewCity() {
        completeQueryWithInputOfCityName(SQL_ADDED_NEW_CITY);

        MyWriter.println("City successfully added.");
        MyWriter.separator();
    }

    public void removingCity() {
        completeQueryWithInputOfCityName(SQL_REMOVING_CITY);

        MyWriter.println("City successfully deleted.");
        MyWriter.separator();
    }

    public void updateCounterOfCities() {
        try (Statement statement = getConnection().createStatement()) {
                statement.executeUpdate(SQL_UPDATE_COUNTER_OF_STUDENT_CITIES_FIRST);
                statement.executeUpdate(SQL_UPDATE_COUNTER_OF_STUDENT_CITIES_SECOND);
        } catch (SQLException e) {
            MyWriter.println("failed request");
            e.printStackTrace();
        }

        MyWriter.println("Counter successfully update.");
        MyWriter.separator();
    }

    private void completeQueryWithInputOfCityName(String sqlQuery) {
        MyWriter.println("Enter city name:");
        String cityName = scanner.nextLine();

        try (PreparedStatement prepStatement = getConnection().prepareStatement(sqlQuery)) {
            prepStatement.setString(1, cityName);
            prepStatement.execute();
        } catch (SQLException e) {
            MyWriter.println("failed request");
            e.printStackTrace();
        }
    }

}
