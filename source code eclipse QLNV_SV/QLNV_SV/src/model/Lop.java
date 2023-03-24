package model;

public class Lop {
	private String malop;
	private String tenlop;
	private String manv;
	
	
	
	public Lop() {
		super();
	}
	public Lop(String malop, String tenlop, String manv) {
		super();
		this.malop = malop;
		this.tenlop = tenlop;
		this.manv = manv;
	}
	public String getMalop() {
		return malop;
	}
	public void setMalop(String malop) {
		this.malop = malop;
	}
	public String getTenlop() {
		return tenlop;
	}
	public void setTenlop(String tenlop) {
		this.tenlop = tenlop;
	}
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getMalop() + "_;_" + this.getTenlop() + "_;_" + this.getManv();
	}
}
