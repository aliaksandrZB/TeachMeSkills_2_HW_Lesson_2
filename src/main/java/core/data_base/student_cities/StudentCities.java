package core.data_base.student_cities;

import core.writer_reader.WriterReader;
import core.data_base.students.Students;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentCities extends Students {

    private static final String SQL_STUDENTS_AND_CITIES = "SELECT students.id, name, last_name, student_cities.city FROM students INNER JOIN student_cities ON students.id = student_cities.id;";

    public void printListOfStudentAndCities() {
        try (Statement statement = super.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_STUDENTS_AND_CITIES);

            while (rs.next()) {
                WriterReader.printDataOfStudentAndHisCity(rs);
            }
            WriterReader.separator();

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addedNewCity() {
        String cityName = WriterReader.enterCityName();

        String sql = "INSERT INTO student_cities (city) VALUES (\"" + cityName + "\");";

        try (Statement statement = super.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        WriterReader.writeAboutSuccessfulAdditionOfCity();
    }

    public void removingCity() {
        String cityName = WriterReader.enterCityName();

        String sql = "DELETE FROM student_cities WHERE city = \"" + cityName + "\";";

        try (Statement statement = super.getConnection().createStatement()) {
                statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        WriterReader.writeAboutSuccessfulRemovalOfCity();
    }

    public void updateCounterOfCities() {
        String sqlFirst = "ALTER TABLE student_cities MODIFY COLUMN id INT UNSIGNED;";
        String sqlSecond = "ALTER TABLE student_cities MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT";

        try (Statement statement = super.getConnection().createStatement()) {
                statement.executeUpdate(sqlFirst);
                statement.executeUpdate(sqlSecond);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
