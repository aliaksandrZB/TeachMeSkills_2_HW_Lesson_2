package core.data_base.students;

import core.writer_reader.WriterReader;
import core.data_base.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Students extends DataBase {

    private static final String SQL = "SELECT * FROM students";

    public void printListOfStudent() {
        try (Statement statement = super.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                WriterReader.printDataOfStudent(rs);
            }
            WriterReader.separator();

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addedNewStudent() {
        WriterReader.writeAddStudent();
        String name = WriterReader.enterStudentName();
        String lastName = WriterReader.enterStudentLastName();

        String sql = "INSERT INTO students (name, last_name)" + " VALUES (\"" + name + "\", \"" + lastName + "\");";

        try (Statement statement = super.getConnection().createStatement()) {
                statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        WriterReader.writeAboutSuccessfulAdditionOfStudent();
    }

    public void removingStudent() {
        WriterReader.writeRemovingStudent();
        String name = WriterReader.enterStudentName();
        String lastName = WriterReader.enterStudentLastName();

        String sql = "DELETE FROM students WHERE name = \"" + name + "\" AND last_name = \"" + lastName + "\";";

        try (Statement statement = super.getConnection().createStatement()) {
                statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        WriterReader.writeAboutSuccessfulRemovalOfStudent();
    }
    public void updateCounterOfStudents() {
        String sqlFirst = "ALTER TABLE students MODIFY COLUMN id INT UNSIGNED;";
        String sqlSecond = "ALTER TABLE students MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT";

        try (Statement statement = super.getConnection().createStatement()) {
            statement.executeUpdate(sqlFirst);
            statement.executeUpdate(sqlSecond);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        WriterReader.writeAboutSuccessfulUpdateOfCounter();
    }

}
