package dataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This class serves to create the database "Omnipredictor" if it does not already exist.
 * It initializes tables: "DJOpening" and "NewsHeadlines" a
 *
 */

public class MySQLInitializer {

	Connection databaseConnection = null;
	
	//localhostID, username, and password are set to default MySQL.These are set in DBConfig;
	private String mySQLConnectionAddress = DBConfig.mySQLConnectionAddress;
	private String databaseConnectionAddress = DBConfig.databaseConnectionAddress;

	public void setUp() throws SQLException{
		if(!DBExists()){
			createDB();
			createDJIATable();
			createNewsStoriesTable();
			}
	}
	
	private boolean DBExists() throws SQLException{
		String query = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'omnipredictor'";
		Connection createConnection = DriverManager.getConnection(mySQLConnectionAddress);
		Statement databaseStatement = createConnection.createStatement();
		ResultSet resultSet = databaseStatement.executeQuery(query);
		if(resultSet.next()){
			return true;
		}
		return false;
	}
	
	private void createDB() throws SQLException{
		String query = "create database omnipredictor";
		Connection createConnection = DriverManager.getConnection(mySQLConnectionAddress);
		Statement databaseStatement = createConnection.createStatement();
		databaseStatement.execute(query);
		databaseConnection = DriverManager.getConnection(databaseConnectionAddress);
	}
	
	private void createDJIATable() throws SQLException{
		String query = "CREATE TABLE `DJOpening` (`id` int(11) NOT NULL,`date` date DEFAULT NULL,`opening` decimal(10,5) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		Statement databaseStatement = databaseConnection.createStatement();
		databaseStatement.execute(query);
		query = "ALTER TABLE `DJOpening` ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `id` (`id`)";
		databaseStatement = databaseConnection.createStatement();
		databaseStatement.execute(query);
		query = "ALTER TABLE `DJOpening` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1470;";
		databaseStatement = databaseConnection.createStatement();
		databaseStatement.execute(query);
	}
	
	private void createNewsStoriesTable() throws SQLException{
		String query = "CREATE TABLE `NewsHeadlines` (`id` int(11) NOT NULL,`date` date DEFAULT NULL,`headline` text) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		Statement databaseStatement = databaseConnection.createStatement();
		databaseStatement.execute(query);
		query = "ALTER TABLE `NewsHeadlines` ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `id` (`id`);";
		databaseStatement = databaseConnection.createStatement();
		databaseStatement.execute(query);
		query = "ALTER TABLE `NewsHeadlines` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1470;";
		databaseStatement = databaseConnection.createStatement();
		databaseStatement.execute(query);
	}
}