package Security;

import model.Lop;
import model.NhanVien;
import model.SinhVien;

public class validations {
	public static boolean valNhanVien(NhanVien nv) {
		int error = 0;
		if(nv.getManv().isBlank()) {
			error++;
		}else if(!nv.getManv().matches("NV[0-9]{2}")) {
			error++;
		}
		
		if(nv.getEmail().trim().equals("")) {
			error++;
		}else if(!nv.getEmail().matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")) {
			error++;
		}
		
		if(nv.getHoten().trim().equals("") || !nv.getHoten().matches("[A-za-z\\s]{1,99}")) {
			error++;
		}
		
		if(nv.getLuong().trim().equals("") || !nv.getLuong().matches("[0-9]{1,}")) {
			error++;
		}
		
		if(nv.getTendn().trim().equals("") || !nv.getTendn().matches("[A-za-z0-9]{1,99}")) {
			error++;
		}
		
		if(nv.getMatkhau().trim().equals("") || !nv.getMatkhau().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
			error++;
		}
		
		if(error != 0) {
			//System.out.println("loi nhap nv");
			return false;
		}
		return true;
	}
	
	public static boolean valLophoc(Lop lp) {
		int error = 0;
		
		if(lp.getMalop().trim().equals("") || !lp.getMalop().matches("LOP[0-9]{2}")) {
			error++;
		}
		
		if(lp.getTenlop().trim().equals("") || !lp.getTenlop().matches("[A-za-z\\s]{1,99}")) {
			error++;
		}
		
		if(error != 0) {
			
			return false;
		}
		return true;
	}
	
	public static boolean valSinhVien(SinhVien sv) {
		int error = 0;
		
		if(sv.getMasv().trim().equals("") || !sv.getMasv().matches("SV[0-9]{3}")) {
			error++;
			//System.out.println("1");
		}
		
		if(sv.getMalop().trim().equals("") || !sv.getMalop().matches("LOP[0-9]{2}")) {
			error++;
			//System.out.println("2");
		}
		
		if(sv.getHoten().trim().equals("") || !sv.getHoten().matches("[A-za-z\\s]{1,99}")) {
			error++;
			//System.out.println("3");
		}
		
		if(sv.getDiachi().trim().equals("") || !sv.getDiachi().matches("[-,A-za-z0-9\\s]{1,199}")) {
			error++;
			//System.out.println("4");
		}
		
		if(sv.getTendn().trim().equals("") || !sv.getTendn().matches("[A-za-z0-9]{1,99}")) {
			error++;
			//System.out.println("5");
		}
		
		if(sv.getMatkhau().trim().equals("") || !sv.getMatkhau().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
			error++;
			//System.out.println("6");
		}
		
		if(error != 0) {
			return false;
		}
		
		return true;
	}
}
