package com.revature.project_0.util.connections;

/**
 *  Follows Singleton Design Pattern
 *      Only 1 instance can exist
 *
 *      creates database connections
 *      relies on db.properties file
 */

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    //0000000000000000000000000000000000000000000000000

    //Holds private instance of ConnectionFactory
    private static final ConnectionFactory connectionFactory = new ConnectionFactory();
    private Properties props = new Properties();

    //0000000000000000000000000000000000000000000000000

    // Just in case static block that forcibly loads postgresql driver
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //Note constructor is private, cannot create additional instances of this class
    private ConnectionFactory() {
        try {
            props.load(new FileReader("src/main/resources/db.properties"));
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    //used to access this class
    public static ConnectionFactory getInstance() {return connectionFactory;}

    //-------------------------------------------------

    //creates connection to database based on credentials in "src/main/resources/db.properties"
    public Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    //-------------------------------------------------

}
