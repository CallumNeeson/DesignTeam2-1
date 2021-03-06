import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataBase.*;

public class NewsStoriesDatabaseTest {
	private List<DataObject> NSList;
	private DatabaseController NSController;
	private static boolean setUp = false;
	
	public void initialize() throws SQLException, ParseException{
		MySQLInitializer initializer = new MySQLInitializer();
		initializer.setUpDatabase();
	}
	
	@Before
	public void setup() throws SQLException, ParseException{
		if(!setUp){
			initialize();
			setUp = true;
		}
		//localhostID, username, and password are set to default MySQL
		String localhostID = "8889";
		String username = "root";
		String password = "root";
		NSWriteStrategy NSWriteStrategy = new NSWriteStrategy();
		DateStringReturnSetStrategy NSReturnStrategy = new DateStringReturnSetStrategy();
		NSController = new DatabaseController(NSWriteStrategy, NSReturnStrategy,"jdbc:mysql://localhost:"+localhostID+"/omnipredictor?user="+ username +"&password=" + password);
	}
	
	@Test
	public void readWriteAllValuesCheckFirstDate() throws SQLException, ParseException {
		
		ResultSet returnList = NSController.retrieveDataFromDB("NewsHeadlines", "2016-07-01", "2016-07-01");
		List<DataObject> dataObjectList = NSController.returnSetStrategy(returnList);
		java.sql.Date correctDate = java.sql.Date.valueOf("2016-07-01");
		assertEquals(correctDate,((DateStringObject) dataObjectList.get(0)).getDate());
	}
	
	@Test 
	public void readWriteAllValuesCheckFirstHeadline() throws SQLException, ParseException {
		
		ResultSet returnList = NSController.retrieveDataFromDB("NewsHeadlines", "2016-07-01", "2016-07-01");
		List<DataObject> dataObjectList = NSController.returnSetStrategy(returnList);
		String correctHeadline = "A 117-year-old woman in Mexico City finally received her birth certificate, and died a few hours later. Trinidad Alvarez Lira had waited years for proof that she had been born in 1898."; 
		assertEquals(correctHeadline,((DateStringObject) dataObjectList.get(0)).getString());
	}
	
	@Test 
	public void readWriteAllValuesCheckTwentyFiveDate() throws SQLException, ParseException {
		
		ResultSet returnList = NSController.retrieveDataFromDB("NewsHeadlines", "2016-07-01", "2016-07-01");
		List<DataObject> dataObjectList = NSController.returnSetStrategy(returnList);
		java.sql.Date correctDate = java.sql.Date.valueOf("2016-07-01");
		assertEquals(correctDate,((DateStringObject) dataObjectList.get(24)).getDate());
	}
	
	@Test 
	public void readWriteAllValuesCheckTwentyFiveHeadline() throws SQLException, ParseException {
		
		ResultSet returnList = NSController.retrieveDataFromDB("NewsHeadlines", "2016-07-01", "2016-07-01");
		List<DataObject> dataObjectList = NSController.returnSetStrategy(returnList);
		String correctHeadline = "Ozone layer hole seems to be healing - US &amp; UK team shows it's shrunk &amp; may slowly recover. \"If you had to have an ozone hole anywhere in the world, it'd be Antarctica because its not teeming with life. It showed us if we didnt back off with these chemicals, wed have a crisis.\"";
		assertEquals(correctHeadline,((DateStringObject) dataObjectList.get(24)).getString());
	}
}
