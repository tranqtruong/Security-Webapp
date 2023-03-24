package model;

import java.util.Base64;

public class SinhVien {
	private String masv;
	private String hoten;
	private String ngaysinh;
	private String diachi;
	private String malop;
	private String tendn;
	private String matkhau;
	private Boolean flag;
	
	public SinhVien() {
		// TODO Auto-generated constructor stub
	}
	
	public SinhVien(String masv, String hoten, String ngaysinh, String diachi, String malop, String tendn) {
		super();
		this.masv = masv;
		this.hoten = hoten;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.malop = malop;
		this.tendn = tendn;
	}
	
	public String getMasv() {
		return masv;
	}
	public void setMasv(String masv) {
		this.masv = masv;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getMalop() {
		return malop;
	}
	public void setMalop(String malop) {
		this.malop = malop;
	}
	public String getTendn() {
		return tendn;
	}
	public void setTendn(String tendn) {
		this.tendn = tendn;
	}
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	
	@Override
	public String toString() {
		String x = this.getMasv() + "_;_" + this.getHoten() + "_;_" + this.getNgaysinh() + "_;_" + this.getDiachi() + "_;_" + this.getMalop() + "_;_" + this.getTendn();
		String encodedString = Base64.getEncoder().encodeToString(x.getBytes());
		return encodedString;
	}
	

}
