package com.revature.project_0.util.connections;

/**
 *  Follows Singleton Design Pattern
 *      Only 1 instance can exist
 *
 *      creates database connections
 *      relies on db.properties file
 */

import com.revature.project_0.util.logger.Logger;

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

    private static Logger logger;

    //0000000000000000000000000000000000000000000000000

    // Just in case static block that forcibly loads postgresql driver
    static {
        logger = Logger.getLogger();

        try {
            Class.forName("org.postgresql.Driver");
            logger.log("Loaded postgresql.Driver successfully");
        } catch (ClassNotFoundException e) {
            logger.log("Could not load postgresql.driver, fatal");
            logger.log(e.getMessage());
            e.printStackTrace();

        }
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //Note constructor is private, cannot create additional instances of this class
    private ConnectionFactory() {

        logger = Logger.getLogger();

        try {
            props.load(new FileReader("src/main/resources/db.properties"));
            logger.log("db.properties loaded successfully");
        }  catch (IOException e) {
            logger.log("Could not load db.properties, fatal");
            logger.log(e.getMessage());
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
            logger.log("Established connection to database.");

        } catch (SQLException e) {
            logger.log("Could not establish connection to database, fatal");
            logger.log(e.getMessage());
            e.printStackTrace();
        }

        logger.log("Database connection created successfully");
        return conn;
    }

    //-------------------------------------------------

}
