package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author SSP
 * This class establishes connection to MySQL and fetches data
 */

public class MySQLConnector {

	Connection con = null;
	public ArrayList<String> name = new ArrayList<String>();
	public ArrayList<String> chat = new ArrayList<String>();
	
	
	/**
	 * This method establishes the MySQL database connection and retrieves data
	 */
	public void CreateConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/plassignment", "root", "1234");
			System.out.println("Connection Successfull");

			// Query to be Executed
			String query = "Select * from plassignment.datastore";

			// Java Statement
			Statement st = con.createStatement();

			// All rows of the results are stored in rs
			ResultSet rs = st.executeQuery(query);

			//Traversing result set
			while (rs.next()) {
				name.add(rs.getString(1));
				chat.add(rs.getString(2));
			}

			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
