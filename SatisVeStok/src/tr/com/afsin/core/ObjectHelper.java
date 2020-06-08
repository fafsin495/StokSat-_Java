package tr.com.afsin.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import tr.com.afsin.interfaces.CoreInterfaces;

public class ObjectHelper extends CoreFields implements CoreInterfaces {
	private static String driver = "com.mysql.jdbc.Driver";
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
