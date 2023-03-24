package controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.accessNhanVien;
import Security.Ciphers;
import Security.Filter;
import Security.validations;
import model.NhanVien;

@Controller
public class NhanVienController {
	@RequestMapping("QLNV")
	public String QLNV(ModelMap model) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		
		//load table Nhan Vien
		List<NhanVien> listStaff = accessNhanVien.listNhanVien();
		model.addAttribute("staffs", listStaff);
		
		try {
			//NhanVien x = accessNhanVien.UserInfo(accessNhanVien.MANV);
			NhanVien x = new NhanVien(accessNhanVien.MANV, accessNhanVien.HOTEN, accessNhanVien.EMAIL, accessNhanVien.TENDN);
			model.addAttribute("NV", x);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "QuanLyNhanVien";
	}
	
	
	@RequestMapping("formNhanVien")
	public String formNhanVien(ModelMap model, HttpServletRequest rq) {
		String chucNang = "";
		
		try {
			chucNang = rq.getParameter("cn");
			chucNang = Filter.cleanXSS(chucNang);
			if(chucNang.equals("sua")) {
				rq.setAttribute("chucnang", "sua");
			}else {
				rq.setAttribute("chucnang", "them");
			}
			
		} catch (Exception e) {
			rq.setAttribute("chucnang", "them");
		}
		
		String error = "";
		try {
			error = rq.getParameter("mes");
			if(error.equals("loimanv")) {
				rq.setAttribute("message", "Mã nhân viên đã tồn tại! Hãy nhập 1 mã khác");
				rq.setAttribute("clk", "btn.click();");
			}else if(error.equals("insertflailed")) {
				rq.setAttribute("message", "Are you a robot?");
				rq.setAttribute("clk", "btn.click();");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
		
		try {
			NhanVien nv = new NhanVien();
			model.addAttribute("nhanvien", nv);
			if(chucNang.equals("sua")) {
				byte[] decodedBytes = Base64.getDecoder().decode(rq.getParameter("nv"));
		        String decodedString = new String(decodedBytes);
		        //System.out.println(decodedString);
		        String[] array = decodedString.split("_;_");
		        array[3] = array[3].replaceAll(" ", "");
				NhanVien nv2 = new NhanVien();
				nv2.setManv(array[0]);
				nv2.setHoten(array[1]);
				nv2.setEmail(array[2]);
				nv2.setLuong(array[3]);
				nv2.setTendn(array[4]);
				//System.out.println(nv2.getLuong());
				model.addAttribute("nhanvien2", nv2);
				model.addAttribute("message1", nv2.getManv());
				model.addAttribute("kt", "/");
				model.addAttribute("MANV", nv2.getManv());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			//NhanVien x = accessNhanVien.UserInfo(accessNhanVien.MANV);
			NhanVien x = new NhanVien(accessNhanVien.MANV, accessNhanVien.HOTEN, accessNhanVien.EMAIL, accessNhanVien.TENDN);
			model.addAttribute("NV", x);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//System.out.println("OK");
		return "nhanvienForm";
	}
	
	
	
	@RequestMapping(value = "themSuaNhanVien", params = "them", method = RequestMethod.POST)
	public String themNhanVien(HttpServletRequest rq, @ModelAttribute("nhanvien") NhanVien nv) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		if(validations.valNhanVien(nv) == false) {
			return "redirect:formNhanVien.htm?cn=them&mes=insertflailed";
		}
		
		//kiểm tra trùng mã nv
		int check = accessNhanVien.kiemTraNv(nv.getManv());
		
		if(check == 1) {//mã nv đã tồn tại trong DB
			//thông báo lỗi
			return "redirect:formNhanVien.htm?cn=them&mes=loimanv";
		}else {
			
			try {
				//mã hóa lương và password
				String passwordHashed = Ciphers.sha1(nv.getMatkhau());
				String luongEncrypted = Ciphers.encryptAES(nv.getLuong());
				
				nv.setMatkhau(passwordHashed);
				nv.setLuong(luongEncrypted);
				//insert to DB
				accessNhanVien.insertNhanVien(nv);
			} catch (Exception e) {
				// TODO: handle exception
			}
				
			
			return "redirect:QLNV.htm";
		}	
	}
	
	@RequestMapping(value = "themSuaNhanVien/{MANV}", params = "sua", method = RequestMethod.POST)
	public String suaNhanVien(ModelMap model, HttpServletRequest rq, @ModelAttribute("nhanvien") NhanVien nv, @PathVariable("MANV") String manvCu) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		if(validations.valNhanVien(nv) == false) {
			rq.setAttribute("message1", manvCu);
			rq.setAttribute("message", "OOps!");
			rq.setAttribute("kt", "/");
			rq.setAttribute("MANV", manvCu);
			rq.setAttribute("chucnang", "sua");
			rq.setAttribute("clk", "btn.click();");
			return "nhanvienForm";
		}
		
		String manvMoi = nv.getManv();
		manvCu = Filter.cleanXSS(manvCu);
		//kiểm tra trùng mã nhâ viên
		int check = accessNhanVien.kiemTraNv(manvCu, manvMoi);
		
		if(check == 1) {//mã nv đã tồn tại trong DB
			//thông báo lỗi
			try {
				rq.setAttribute("message1", manvCu);
				rq.setAttribute("message", "Mã nhân viên đã tồn tại!");
				rq.setAttribute("kt", "/");
				rq.setAttribute("MANV", manvCu);
				rq.setAttribute("chucnang", "sua");
				rq.setAttribute("clk", "btn.click();");
			} catch (Exception e) {
				// TODO: handle exception
			}
				
			return "nhanvienForm";
		}else {
			try {
				//update nhan vien
				accessNhanVien.updateNhanVien(manvCu, nv);
				
				//load table Nhan Vien
				List<NhanVien> listStaff = accessNhanVien.listNhanVien();
				model.addAttribute("staffs", listStaff);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return "QuanLyNhanVien";
		}
		
	}
	
	
	@RequestMapping("xoaNhanVien")
	public String xoaNhanVien(HttpServletRequest rq ) {
			
		try {
			String MANV = rq.getParameter("mnv");
			MANV = Filter.cleanXSS(MANV);
			accessNhanVien.deleteNhanVien(MANV);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:QLNV.htm";
	}
	
}
