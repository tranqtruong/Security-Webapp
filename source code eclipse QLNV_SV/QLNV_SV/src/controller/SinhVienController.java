package controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.accessNhanVien;
import DAO.accessSinhVien;
import Security.Ciphers;
import Security.Filter;
import Security.validations;
import model.NhanVien;
import model.SinhVien;

@Controller
public class SinhVienController {
	@RequestMapping("QLSV")
	public String QLSV(ModelMap model, HttpServletRequest rq) {
		//load table
		List<SinhVien> dsSv = new ArrayList<SinhVien>();
		dsSv = accessSinhVien.loadSinhVien();
		model.addAttribute("students", dsSv);
		
		try {
			//NhanVien x = accessNhanVien.UserInfo(accessNhanVien.MANV);
			NhanVien x = new NhanVien(accessNhanVien.MANV, accessNhanVien.HOTEN, accessNhanVien.EMAIL, accessNhanVien.TENDN);
			model.addAttribute("NV", x);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "QuanLySinhVien";
	}
	
	
	@RequestMapping(value = "goformsv")
	public String goformsv(HttpServletRequest rq, ModelMap model) {
		SinhVien sv = new SinhVien();
		model.addAttribute("sinhvien", sv);
		
	    String chucNang;
	    try {
	    	chucNang = rq.getParameter("cn");
		    
		} catch (Exception e) {
			chucNang = "them";
		}
	    
	    try {
	    	chucNang = Filter.cleanXSS(chucNang);
	    	if(chucNang.equals("sua")) {
	    		rq.setAttribute("chucnang", "sua");
	    	}else {
	    		rq.setAttribute("chucnang", "them");
	    	}
		    
		    
		    if(chucNang.equals("sua")) {
		    	SinhVien sv2 = new SinhVien();
		    	
		    	byte[] decodedBytes = Base64.getDecoder().decode(rq.getParameter("sv"));
		        String decodedString = new String(decodedBytes);
		        
		        String[] array = decodedString.split("_;_");
		        sv2.setMasv(array[0]);
		        sv2.setHoten(array[1]);
		        sv2.setNgaysinh(array[2]);
		        sv2.setDiachi(array[3]);
		        sv2.setMalop(array[4]);
		        sv2.setTendn(array[5]);
		        
		        model.addAttribute("sinhvien2", sv2);
		        model.addAttribute("message1", sv2.getMasv());
				model.addAttribute("kt", "/");
				model.addAttribute("MASV", sv2.getMasv());
		    }
		    
			List<String> dsLop = accessSinhVien.loadComboBoxMALOP();
			rq.setAttribute("ids", dsLop);
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
		try {
			//NhanVien x = accessNhanVien.UserInfo(accessNhanVien.MANV);
			NhanVien x = new NhanVien(accessNhanVien.MANV, accessNhanVien.HOTEN, accessNhanVien.EMAIL, accessNhanVien.TENDN);
			model.addAttribute("NV", x);
		} catch (Exception e) {
			
		}
		
		try {
			String message = rq.getParameter("mess");
			if(message.equals("loimasv")) {
				rq.setAttribute("message", "Mã sinh viên đã được sử dụng. Vui lòng nhập 1 mã khác!");
				rq.setAttribute("clk", "btn.click();");
			}else if(message.equals("robot")) {
				rq.setAttribute("message", "Are u robot?");
				rq.setAttribute("clk", "btn.click();");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "sinhVienForm";
	}
	
	@RequestMapping(value = "themSuaSinhVien", params = "them", method = RequestMethod.POST)
	public String themSinhVien(@ModelAttribute("sinhvien") SinhVien sv) throws NoSuchAlgorithmException {
		if(validations.valSinhVien(sv) == false) {
			return "redirect:goformsv.htm?cn=them&mess=robot";
		}
		
		
		//kiểm tra trùng mã sv
		int checkMa = accessSinhVien.checkSinhVien(sv.getMasv());
		
		if(checkMa == 1) {
			return "redirect:goformsv.htm?cn=them&mess=loimasv";
		}else {
			String password = sv.getMatkhau();
			String passwordHashed = Ciphers.sha1(password);
			sv.setMatkhau(passwordHashed);
			accessSinhVien.insertSinhVien(sv);
			return "redirect:QLSV.htm";
		}
		
	}
	
	@RequestMapping(value = "themSuaSinhVien/{MASV}", params = "sua", method = RequestMethod.POST)
	public String suaSinhVien(HttpServletRequest rq, @ModelAttribute("sinhvien") SinhVien sv, @PathVariable("MASV") String masvCu) {
		if(validations.valSinhVien(sv) == false) {
			rq.setAttribute("chon", 7);
			return "redirect";
		}
		
		String masvMoi = sv.getMasv();
		
		int checkma = accessSinhVien.checkSinhVien(masvCu, masvMoi);
			
		if(checkma == 1) {
			rq.setAttribute("chon", 4);
			return "redirect";
		}else {
			accessSinhVien.updateSinhVien(masvCu, sv);
			rq.setAttribute("chon", 5);
			return "redirect";
		}
	}
	
	@RequestMapping("xoaSinhVien")
	public String xoaSinhVien(HttpServletRequest rq) {
		try {
			String masv = rq.getParameter("msv");
			accessSinhVien.deleteSinhVien(masv);
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		return "redirect:QLSV.htm";
	}
	
	@RequestMapping(value = "timkiem", method = RequestMethod.POST)
	public String search(HttpServletRequest rq, ModelMap model) {
		String keySearh = rq.getParameter("ma");
		keySearh = Filter.cleanXSS(keySearh);
		if(keySearh.toUpperCase().equals("ALL")) {
			return "redirect:QLSV.htm";
		}
		
		//load kết quả
		List<SinhVien> ds = accessSinhVien.search(keySearh.toUpperCase());
		if(!ds.isEmpty()) {
			model.addAttribute("students", ds);
		}else {
			rq.setAttribute("result", "Sorry! No result for " + keySearh);
		}
		
		try {
			//NhanVien x = accessNhanVien.UserInfo(accessNhanVien.MANV);
			NhanVien x = new NhanVien(accessNhanVien.MANV, accessNhanVien.HOTEN, accessNhanVien.EMAIL, accessNhanVien.TENDN);
			model.addAttribute("NV", x);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "QuanLySinhVien";
	}
	
}
