package core;

import core.service.crud.StudentCRUDService;
import core.service.MySQLConnector;
import core.service.crud.StudentCitiesCRUDService;

public class MyApp {

    private static final int COMPLETE_PROGRAM = 0;

    public static void main(String[] args) {

        MySQLConnector connector = new MySQLConnector();

        StudentCRUDService studentCRUDService = new StudentCRUDService(connector);
        StudentCitiesCRUDService studentCitiesCRUDService = new StudentCitiesCRUDService(connector);
        ApplicationMenu applicationMenu = new ApplicationMenu();

        int choiceFunction;
        do {
            applicationMenu.displayInterface();
            choiceFunction = applicationMenu.chooseFunction(studentCRUDService, studentCitiesCRUDService);
        } while (choiceFunction != COMPLETE_PROGRAM);

        studentCRUDService.scanner.close();
        studentCitiesCRUDService.scanner.close();

        studentCRUDService.closeConnection();
        studentCitiesCRUDService.closeConnection();

    }

}
