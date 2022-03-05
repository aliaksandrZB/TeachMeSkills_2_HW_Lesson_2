package core.data_base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DataBase {

    private static final String PATH_CONNECTOR_PROPERTIES = "src" + File.separator + "main" + File.separator +
                                                     "resources" + File.separator + "connectorData";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void connect() {
        Properties connectorProps = new Properties();
        try (FileInputStream file = new FileInputStream(PATH_CONNECTOR_PROPERTIES)) {
            connectorProps.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = connectorProps.getProperty("url");
        String user = connectorProps.getProperty("user");
        String password = connectorProps.getProperty("password");

        try {
            Class.forName(DB_DRIVER);
            setConnection(DriverManager.getConnection(url, user, password));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnector() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
