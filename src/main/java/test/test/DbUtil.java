package test.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtil {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/indi_pro", "root", "");
	}

	public static void main(String[] args) {
		connection();
	}

	public static List<Map<String,String>> execteQuerySql(String sql) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		Connection conn;
		try {
			conn = getConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				Map<String,String> map = new HashMap<String,String>();
				for (int i=0;i<rsmd.getColumnCount();i++) {
					map.put(rsmd.getColumnName(i+1), rs.getObject(rsmd.getColumnName(i+1)).toString());
				}
				list.add(map);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} finally {
		}
		return list;
	}

	public static void executeUpdateSql(String sql) throws ClassNotFoundException {
		System.out.println(sql);
		Connection conn;
		try {
			conn = getConnection();

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

//			e.printStackTrace();
		} finally {
//			if (!conn.isClosed()) {
//				conn.close();
//			}
		}
	}

	public static void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/indi_pro", "root", "");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from ssq");
			while (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getObject("red2"));
			}

			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
}
