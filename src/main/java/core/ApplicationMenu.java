package core;

import core.service.MyWriter;
import core.service.crud.StudentCRUDService;
import core.service.crud.StudentCitiesCRUDService;

import java.util.Scanner;

public class ApplicationMenu {

    private static final int PRINT_LIST_OF_STUDENT = 1;
    private static final int PRINT_LIST_OF_STUDENT_AND_CITIES = 2;
    private static final int ADDED_NEW_STUDENT = 3;
    private static final int ADDED_NEW_CITY = 4;
    private static final int REMOVING_STUDENT = 5;
    private static final int REMOVING_CITY = 6;
    private static final int UPDATE_COUNTER = 7;
    private static final int COMPLETE_PROGRAM = 0;

    private Scanner scanner;

    public ApplicationMenu() {
        this.scanner = new Scanner(System.in);
    }

    public void displayInterface() {
        MyWriter.println("1 - Display student data.");
        MyWriter.println("2 - Display data of students and their cities.");
        MyWriter.println("3 - Add a student.");
        MyWriter.println("4 - Add city.");
        MyWriter.println("5 - Delete a student.");
        MyWriter.println("6 - Delete city.");
        MyWriter.println("7 - Update the counter of students and cities.");
        MyWriter.println("0 - Complete the program.");
        MyWriter.println("\nYour choice: ");
    }

    public int chooseFunction(StudentCRUDService studentCRUDService, StudentCitiesCRUDService studentCitiesCRUDService) {
        int choiceFunction = scanner.nextInt();
        MyWriter.separator();

        switch (choiceFunction) {
            case PRINT_LIST_OF_STUDENT:
                studentCRUDService.printListOfStudent();
                break;
            case PRINT_LIST_OF_STUDENT_AND_CITIES:
                studentCitiesCRUDService.printListOfStudentAndCities();
                break;
            case ADDED_NEW_STUDENT:
                studentCRUDService.addedNewStudent();
                break;
            case ADDED_NEW_CITY:
                studentCitiesCRUDService.addedNewCity();
                break;
            case REMOVING_STUDENT:
                studentCRUDService.removingStudent();
                break;
            case REMOVING_CITY:
                studentCitiesCRUDService.removingCity();
                break;
            case UPDATE_COUNTER:
                studentCRUDService.updateCounterOfStudents();
                studentCitiesCRUDService.updateCounterOfCities();
                break;
            case COMPLETE_PROGRAM:
                break;
            default:
                MyWriter.println("Wrong choice, try again");
                MyWriter.separator();
        }

        return choiceFunction;
    }

}
