package cn.edu.zucc.personplan.util;

import java.sql.Connection;
import java.util.Properties;

public class DBUtil {
	private static  String jdbcUrl ;
	private static  String dbUser;
	private static  String dbPwd ;
	private static  String Driver ;
	static{
		try {
			Properties prop = new Properties();
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream("DBconfig.properties"));
			Driver = prop.getProperty("Driver");
			dbUser = prop.getProperty("dbUser");
			dbPwd = prop.getProperty("dbPwd");
			jdbcUrl = prop.getProperty("jdbcUrl");
			Class.forName(Driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws java.sql.SQLException{
		return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
	}
}
