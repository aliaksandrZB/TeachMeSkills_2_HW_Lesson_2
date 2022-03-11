package core.service.crud;

import core.service.MyWriter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class CRUDService {

    private Connection connection;
    public Scanner scanner;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            MyWriter.println("failed to close connector");
            e.printStackTrace();
        }
    }

}
