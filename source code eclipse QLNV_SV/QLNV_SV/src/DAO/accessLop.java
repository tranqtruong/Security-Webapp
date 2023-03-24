package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Lop;

public class accessLop {
	
	public static List<String> loadcomboBoxMANV(){
		List<String> listMaNv = new ArrayList<String>();
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "select MANV from NHANVIEN";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String MANV = rs.getString("MANV");
				listMaNv.add(MANV);
			}
			rs.close();
			ps.close();
			kn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return listMaNv;	
	}
	
	public static List<Lop> loadTableLop(){
		List<Lop> listLop = new ArrayList<Lop>();
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "select * from LOP";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String MALOP = rs.getString("MALOP");
				String TENLOP = rs.getString("TENLOP");
				String MANV = rs.getString("MANV");
				
				Lop lop = new Lop(MALOP, TENLOP, MANV);
				listLop.add(lop);
			}
			rs.close();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return listLop;
	}
	
	public static int checkMaLop(String malop) {
		int tonTai = 0;
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "select MALOP from LOP where MALOP = ?";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, malop);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				tonTai = 1;
			}
			rs.close();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return tonTai;
	}
	
	public static int checkMaLop(String maLopCu, String malopMoi) {
		int tonTai = 0;
		Connection kn = ConnectionMSSQL.layKetNoi();
		//String sql = "select * from (select MALOP AS MALOP2 from LOP where MALOP <> '"+maLopCu+"') AS T where T.MALOP2 = '"+malopMoi+"'";
		String sql = "select * from (select MALOP AS MALOP2 from LOP where MALOP <> ?) AS T where T.MALOP2 = ?";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, maLopCu);
			ps.setString(2, malopMoi);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				tonTai = 1;
			}
			rs.close();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return tonTai;
	}
	
	public static void insertLop(Lop lp) {
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "insert into LOP values(?, ?, ?)";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, lp.getMalop());
			ps.setString(2, lp.getTenlop());
			ps.setString(3, lp.getManv());
			ps.executeUpdate();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	public static void updateLop(String malopCu, Lop lp) {
		Connection kn = ConnectionMSSQL.layKetNoi();
		//String sql = "update LOP set MALOP = '" + lp.getMalop() + "', TENLOP = '" + lp.getTenlop() + "', MANV = '" + lp.getManv() + "' where MALOP = '" + malopCu + "'";
		String sql = "update LOP set MALOP = ?, TENLOP = ?, MANV = ? where MALOP = ?";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, lp.getMalop());
			ps.setString(2, lp.getTenlop());
			ps.setString(3, lp.getManv());
			ps.setString(4, malopCu);
			ps.executeUpdate();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	public static void deleteLop(String malop) {
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "delete from LOP where MALOP = ?";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, malop);
			ps.executeUpdate();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
}
