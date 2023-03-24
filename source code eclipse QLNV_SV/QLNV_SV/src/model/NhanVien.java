package model;

import java.util.Base64;

public class NhanVien {
	private String manv;
	private String hoten;
	private String email;
	private String luong;
	private String tendn;
	private String matkhau;
	private Boolean flag;
	
	public NhanVien() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public NhanVien(String manv, String hoten, String email, String tendn) {
		super();
		this.manv = manv;
		this.hoten = hoten;
		this.email = email;
		this.tendn = tendn;
	}



	public NhanVien(String manv, String hoten, String email, String luong, String tendn, Boolean flag) {
		super();
		this.manv = manv;
		this.hoten = hoten;
		this.email = email;
		this.luong = luong;
		this.tendn = tendn;
		this.flag = flag;
	}
	
	

	public NhanVien(String manv, String hoten, String email, String luong, String tendn, String matkhau) {
		super();
		this.manv = manv;
		this.hoten = hoten;
		this.email = email;
		this.luong = luong;
		this.tendn = tendn;
		this.matkhau = matkhau;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLuong() {
		return luong;
	}

	public void setLuong(String luong) {
		this.luong = luong;
	}

	public String getTendn() {
		return tendn;
	}

	public void setTendn(String tendn) {
		this.tendn = tendn;
	}
	
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	public String getMatkhau() {
		return matkhau;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		String x = this.manv + "_;_" + this.hoten + "_;_" + this.email + "_;_" + this.luong + "_;_" + this.tendn;
		String encodedString = Base64.getEncoder().encodeToString(x.getBytes());
		return encodedString;
	}
	
	
	
}
