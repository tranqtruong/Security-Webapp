package DAO;

import java.sql.Connection;
import java.sql.DriverManager;



public class ConnectionMSSQL {
	
	public static Connection layKetNoi() {
		Connection ketnoi = null;
		String uRl = "jdbc:sqlserver://DESKTOP-L65OT1A:1433;databaseName=QLSV;integratedSecurity=true;";
		String username = "sa";
		String password = "123";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ketnoi = DriverManager.getConnection(uRl, username, password);
			//System.out.println("Ket noi CSDL thanh cong.");
		} catch (Exception e) {
			//System.out.println("Khong ket noi duoc voi CSDL!");
			//System.err.println(e.getMessage()+"\n" + e.getClass() + "\n" + e.getCause());
		}
	    
		return ketnoi;
	}
	
}
