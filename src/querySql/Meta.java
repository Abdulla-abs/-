package querySql;

import java.sql.Connection;
import java.sql.DriverManager;

public class Meta {
	private static String jdbcName = "com.mysql.cj.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://localhost:3306/db_text";
	private static String dbUserName = "root";
	private static String dbPassword = "200111";

	public Connection getConnection() throws Exception {
		Class.forName(jdbcName);
		Connection connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return connection;
	}

}
