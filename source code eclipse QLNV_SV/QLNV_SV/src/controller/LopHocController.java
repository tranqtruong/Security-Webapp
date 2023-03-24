package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.accessLop;
import DAO.accessNhanVien;
import Security.validations;
import model.Lop;
import model.NhanVien;


@Controller
public class LopHocController {
	@RequestMapping("QLLH")
	public String QLLH(HttpServletRequest rq , ModelMap model) {
		Lop lp = new Lop();
		model.addAttribute("lop", lp);
		
		String chucNang = ""; 
		try {
			chucNang = rq.getParameter("cn");
			if(chucNang.equals("sua")) {
				rq.setAttribute("updateChecked", "checked=\"checked\"");
			}else {
				chucNang = "them";
				rq.setAttribute("insertChecked", "checked=\"checked\"");
			}
		} catch (Exception e) {
			rq.setAttribute("insertChecked", "checked=\"checked\"");
			chucNang = "them";
		}
		
		try {
			rq.setAttribute("chucnang", chucNang);
			
			//nếu chức năng là sửa thì đổ dữ liệu lớp học cần sửa vào form
			if(chucNang.equals("sua")) {
				String lop = rq.getParameter("lp");
				String[] manglop = lop.split("_;_");
				
				Lop lp2 = new Lop(manglop[0], manglop[1], manglop[2]);
				model.addAttribute("lop2", lp2);
				rq.setAttribute("manvsua", lp2.getMalop());
				
				rq.setAttribute("kt", "/");
				rq.setAttribute("MALOP", lp2.getMalop());
			}
			
			//load combobox manv
			List<String> listMaNv = accessLop.loadcomboBoxMANV();
			rq.setAttribute("ids", listMaNv);
			
			//load table LOP
			List<Lop> listLop = accessLop.loadTableLop();
			
			model.addAttribute("classes", listLop);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
		
		//nếu thêm/sửa mã lớp bị trùng thì hiển thị thông báo
		String error = "";
		try {
			error = rq.getParameter("check");
			if(error.equals("loimnv")) {
				rq.setAttribute("message", "Không thể thực hiện yêu cầu do mã nhân viên đã được sử dụng. Vui lòng nhập một mã khác!");
				rq.setAttribute("clk", "btn.click();");
			}else if(error.equals("loimnv1")) {
				rq.setAttribute("message", "Không thể chỉnh sửa do lớp không tồn tại. Vui lòng nhập một mã lớp khác!");
				rq.setAttribute("clk", "btn.click();");
			}else if(error.equals("loimnv2")) {
				rq.setAttribute("message", "!!!");
				rq.setAttribute("clk", "btn.click();");
			}
		} catch (Exception e) {
			
		}
		
		try {
			//NhanVien x = accessNhanVien.UserInfo(accessNhanVien.MANV);
			NhanVien x = new NhanVien(accessNhanVien.MANV, accessNhanVien.HOTEN, accessNhanVien.EMAIL, accessNhanVien.TENDN);
			model.addAttribute("NV", x);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "QuanLyLopHoc";
	}
	
	
	
	@RequestMapping(value = "themSuaLop", params = "them", method = RequestMethod.POST)
	public String themLop(ModelMap model, HttpServletRequest rq, @ModelAttribute("lop") Lop lp) {
		if(validations.valLophoc(lp) == false) {
		 	return "redirect:QLLH.htm?check=loimnv2";
		}
		
		//kiểm tra nếu check sửa
		String cn = rq.getParameter("customRadioInline1");
		//kiểm tra mã lớp trùng
		int check = accessLop.checkMaLop(lp.getMalop());
		if(cn.equals("sua")) {
			if(check == 1) {
				//cho phép sửa
				try {
					accessLop.updateLop(lp.getMalop(), lp);
				} catch (Exception e) {
					// TODO: handle exception
				}
					
				return "redirect:QLLH.htm";
			}else {
				return "redirect:QLLH.htm?check=loimnv1";
			}
		}else {
			if(check == 1) {
				return "redirect:QLLH.htm?check=loimnv";
			}else {
				try {
					accessLop.insertLop(lp);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				return "redirect:QLLH.htm";
			}
		}

	}
	
	@RequestMapping(value = "themSuaLop/{MALOP}", params = "sua", method = RequestMethod.POST)
	public String suaLop(ModelMap model, HttpServletRequest rq, @ModelAttribute("lop") Lop lp, @PathVariable("MALOP") String malopCu) {
		if(validations.valLophoc(lp) == false) {
			rq.setAttribute("chon", 6);
		 	return "redirect";
		}
		
		String cn = rq.getParameter("customRadioInline1");
		//System.out.println(cn);
		if(cn.equals("sua")) {
			
			try {
				int ck = accessLop.checkMaLop(malopCu, lp.getMalop());
				
				if(ck == 1) {
					//báo lỗi mã bị trùng
					rq.setAttribute("chon", 1);
					
				}else {
					accessLop.updateLop(malopCu, lp);
					rq.setAttribute("chon", 2);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
				
		}else {
			try {
				int ck = accessLop.checkMaLop(lp.getMalop());
				if(ck == 1) {
					rq.setAttribute("chon", 3);
					
				}else {
					accessLop.insertLop(lp);
					rq.setAttribute("chon", 2);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
		return "redirect";
	}
	
	
	@RequestMapping("xoaLop")
	public String xoaLop(@RequestParam("ml") String malop) {
		try {
			accessLop.deleteLop(malop);
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		return "redirect:QLLH.htm";
	}
}
