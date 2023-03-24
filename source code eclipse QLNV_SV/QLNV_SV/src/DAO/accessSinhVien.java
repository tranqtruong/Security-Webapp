package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Security.Ciphers;
import model.SinhVien;

public class accessSinhVien {
	
	public static List<SinhVien> loadSinhVien() {
		List<SinhVien> list = new ArrayList<SinhVien>();
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "select MASV, HOTEN, NGAYSINH, DIACHI, MALOP, TENDN from SINHVIEN";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String MASV = rs.getString("MASV");
				String HOTEN = rs.getString("HOTEN");
				String NGAYSINH = rs.getDate("NGAYSINH").toString();
				String DIACHI = rs.getString("DIACHI");
				String MALOP = rs.getString("MALOP");
				String TENDN = rs.getString("TENDN");
				
				SinhVien sv = new SinhVien(MASV, HOTEN, NGAYSINH, DIACHI, MALOP, TENDN);
				list.add(sv);
			}
			rs.close();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return list;
	}
	
	public static List<String> loadComboBoxMALOP(){
		List<String> dsLop = new ArrayList<String>();
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "select MALOP from LOP";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String malop = rs.getString("MALOP");
				dsLop.add(malop);
			}
			rs.close();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return dsLop;
	}
	
	public static void insertSinhVien(SinhVien sv) {
		Connection kn = ConnectionMSSQL.layKetNoi();
		//String sql = "exec insert_sinhvien '" + sv.getMasv() + "', '" + sv.getHoten() +"', '"+ sv.getNgaysinh() +"', '" + sv.getDiachi() + "', '" + sv.getMalop() + "','" + sv.getTendn() + "', "+ sv.getMatkhau() +", 0";
		String sql = "exec insert_sinhvien ?, ?, ?, ?, ?, ?, ?, 0";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, sv.getMasv());
			ps.setString(2, sv.getHoten());
			ps.setDate(3, Date.valueOf(sv.getNgaysinh()));
			ps.setString(4, sv.getDiachi());
			ps.setString(5, sv.getMalop());
			ps.setString(6, sv.getTendn());
			ps.setBytes(7, Ciphers.hex2Byte(sv.getMatkhau().substring(2)));
			ps.executeUpdate();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	public static int checkSinhVien(String masv) {
		int tonTai = 0;
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "select MASV from SINHVIEN where MASV = ?";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, masv);
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
	
	public static int checkSinhVien(String masvCu, String masvMoi) {
		int tonTai = 0;
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "select T.MASV2 from (select MASV AS MASV2 from SINHVIEN where MASV <> ?) AS T where T.MASV2 = ?";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, masvCu);
			ps.setString(2, masvMoi);
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
	
	public static void updateSinhVien(String masvCu, SinhVien sv) {
		Connection kn = ConnectionMSSQL.layKetNoi();
		//String sql = "exec update_sinhvien '"+masvCu+"', '"+sv.getMasv()+"', '"+sv.getHoten()+"', '"+sv.getNgaysinh()+"', '"+sv.getDiachi()+"', '"+sv.getMalop()+"', '"+sv.getTendn()+"', '"+sv.getMatkhau()+"', 1";
		String sql = "exec update_sinhvien ?, ?, ?, ?, ?, ?, ?, ?, 1";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, masvCu);
			ps.setString(2, sv.getMasv());
			ps.setString(3, sv.getHoten());
			ps.setDate(4, Date.valueOf(sv.getNgaysinh()));
			ps.setString(5, sv.getDiachi());
			ps.setString(6, sv.getMalop());
			ps.setString(7, sv.getTendn());
			ps.setString(8, sv.getMatkhau());
			ps.executeUpdate();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	
	public static void deleteSinhVien(String masv) {
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "delete from SINHVIEN where MASV = ?";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, masv);
			ps.executeUpdate();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	public static List<SinhVien> search(String keyword){
		List<SinhVien> result = new ArrayList<SinhVien>();
		int tonTai = 0;
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "select MASV, HOTEN, NGAYSINH, DIACHI, MALOP, TENDN from SINHVIEN where MASV = ?";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, keyword);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tonTai = 1;
				String MASV = rs.getString("MASV");
				String HOTEN = rs.getString("HOTEN");
				String NGAYSINH = rs.getDate("NGAYSINH").toString();
				String DIACHI = rs.getString("DIACHI");
				String MALOP = rs.getString("MALOP");
				String TENDN = rs.getString("TENDN");
				
				SinhVien sv = new SinhVien(MASV, HOTEN, NGAYSINH, DIACHI, MALOP, TENDN);
				result.add(sv);
			}
			rs.close();
			ps.close();
			kn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if(tonTai == 1) {
			return result;
		}else {
			Connection kn2 = ConnectionMSSQL.layKetNoi();
			String sql2 = "select MASV, HOTEN, NGAYSINH, DIACHI, MALOP, TENDN from SINHVIEN where MALOP = ?";
			try {
				PreparedStatement ps2 = kn2.prepareStatement(sql2);
				ps2.setString(1, keyword);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()) {
					String MASV = rs2.getString("MASV");
					String HOTEN = rs2.getString("HOTEN");
					String NGAYSINH = rs2.getDate("NGAYSINH").toString();
					String DIACHI = rs2.getString("DIACHI");
					String MALOP = rs2.getString("MALOP");
					String TENDN = rs2.getString("TENDN");
					
					SinhVien sv = new SinhVien(MASV, HOTEN, NGAYSINH, DIACHI, MALOP, TENDN);
					result.add(sv);
				}
				rs2.close();
				ps2.close();
				kn2.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			return result;
		}
		
			
	}
}
