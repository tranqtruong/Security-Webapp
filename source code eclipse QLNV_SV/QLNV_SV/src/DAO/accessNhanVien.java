package DAO;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import Security.Ciphers;
import model.NhanVien;

public class accessNhanVien {
	public static String MANV;
	public static String HOTEN;
	public static String EMAIL;
	public static String TENDN;
	
	
	public static NhanVien UserInfo(String manv) {
		NhanVien nv = new NhanVien();
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "select * from NHANVIEN where MANV = '" + manv + "'";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String MANV = rs.getString("MANV");
                String HOTEN = rs.getString("HOTEN");
                String EMAIl = rs.getString("EMAIL");
                String TENDN = rs.getString("TENDN");
                nv.setManv(MANV);
                nv.setHoten(HOTEN);
                nv.setEmail(EMAIl);
                nv.setTendn(TENDN);
			}
			rs.close();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return nv;
	}
	
	//load table nhan vien
	public static List<NhanVien> listNhanVien() throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		List<NhanVien> listStaff = new ArrayList<NhanVien>();
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "exec load_table_nhanvien";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String MANV = rs.getString("MANV");
                String HOTEN = rs.getString("HOTEN");
                String EMAIl = rs.getString("EMAIL");
                byte[] LUONG = rs.getBytes("LUONGCB");
                String TENDN = rs.getString("TENDN");
                Boolean FLAG = rs.getBoolean("FLAG");
                
                String LUONGCB = "";//lương giải mã
                
                if(FLAG == true){//nếu flag = 1 có nghĩa dữ liệu mã hóa ở phía database đã được giải mã
                    LUONGCB = new String(LUONG, StandardCharsets.UTF_16LE);
                    //System.out.println(LUONGCB);
                }else {//ngược lại flag = 0 thì dữ liệu luong mã hóa phía client chưa được giải mã 
                	LUONGCB = Ciphers.decryptAES(LUONG);
                }
                
                NhanVien nv = new NhanVien(MANV, HOTEN, EMAIl, LUONGCB, TENDN, FLAG);
                //System.out.println(nv.getManv() + " " + nv.getLuong());
                listStaff.add(nv);
			}
			rs.close();
            ps.close();
            kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return listStaff;
	}
	
	//kiểm tra trùng manv
	public static int kiemTraNv(String manv) {
		int tonTai = 0;
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql1 = "select MANV from NHANVIEN where MANV = ?";
		try {
			PreparedStatement ps = kn.prepareStatement(sql1);
			ps.setString(1, manv);
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
	public static int kiemTraNv(String manvCu, String manvMoi) {
		int tonTai = 0;
		Connection kn = ConnectionMSSQL.layKetNoi();
		//String sql2 = "select * from (select MANV AS MANV2 from NHANVIEN where MANV <> '" + manvCu + "') AS T where T.MANV2 = '" + manvMoi +"'";
		String sql2 = "select * from (select MANV AS MANV2 from NHANVIEN where MANV <> ?) AS T where T.MANV2 = ?";
		try {
			PreparedStatement ps = kn.prepareStatement(sql2);
			ps.setString(1, manvCu);
			ps.setString(2, manvMoi);
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
	
	//thêm nhân viên
	public static void insertNhanVien(NhanVien nv) {
		Connection kn = ConnectionMSSQL.layKetNoi();
		//String sql = "exec insert_nhanvien '" + nv.getManv() + "', '" + nv.getHoten() + "', '" + nv.getEmail() + "', " + nv.getLuong() + ", '" + nv.getTendn() + "', " + nv.getMatkhau() + ", " + 0;
		String sql = "exec insert_nhanvien ?, ?, ?, ?, ?, ?, 0";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, nv.getManv());
			ps.setString(2, nv.getHoten());
			ps.setString(3, nv.getEmail());
			ps.setBytes(4, Ciphers.hex2Byte(nv.getLuong().substring(2)));
			ps.setString(5, nv.getTendn());
			ps.setBytes(6, Ciphers.hex2Byte(nv.getMatkhau().substring(2)));
			ps.executeUpdate();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
	}
	
	//sửa nhân viên 
	public static void updateNhanVien(String manvCu, NhanVien nv) {
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "exec update_nhanvien ?, ?, ?, ?, ?, ?, ?, 1";
		//String sql = "exec update_nhanvien '"+ manvCu + "', '" + nv.getManv() + "', '" + nv.getHoten() + "', '" + nv.getEmail() + "', " + nv.getLuong() + ", '" + nv.getTendn() + "', '" + nv.getMatkhau() + "', " + 1;
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, manvCu);
			ps.setString(2, nv.getManv());
			ps.setString(3, nv.getHoten());
			ps.setString(4, nv.getEmail());
			ps.setInt(5, Integer.parseInt(nv.getLuong()));
			ps.setString(6, nv.getTendn());
			ps.setString(7, nv.getMatkhau());
			ps.executeUpdate();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	//xóa nhân viên
	public static void deleteNhanVien(String manv) {
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "delete from NHANVIEN where MANV = ?";
		PreparedStatement ps;
		try {
			ps = kn.prepareStatement(sql);
			ps.setString(1, manv);
			ps.executeUpdate();
			ps.close();
			kn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		
	}
	
	
}
