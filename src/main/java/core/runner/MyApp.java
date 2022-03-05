package core.runner;

import core.writer_reader.WriterReader;
import core.data_base.student_cities.StudentCities;
import core.data_base.students.Students;

import java.util.Scanner;

public class MyApp {

    public static void main(String[] args) {

        Students students = new Students();
        students.connect();

        StudentCities studentCities = new StudentCities();
        studentCities.connect();

        int choice = -1;
        while (choice != 0) {
            WriterReader.callMenu();
            choice = selectTask(students, studentCities);
        }

        students.closeConnector();
        studentCities.closeConnector();

    }

    private static int selectTask(Students students, StudentCities studentCities) {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        WriterReader.separator();
        switch (choice) {
            case 1:
                students.printListOfStudent();
                break;
            case 2:
                studentCities.printListOfStudentAndCities();
                break;
            case 3:
                students.addedNewStudent();
                break;
            case 4:
                studentCities.addedNewCity();
                break;
            case 5:
                students.removingStudent();
                break;
            case 6:
                studentCities.removingCity();
                break;
            case 7:
                students.updateCounterOfStudents();
                studentCities.updateCounterOfCities();
                break;
            case 0:
                break;
            default:
                WriterReader.writeWrongChoice();
        }

        return choice;
    }

}
