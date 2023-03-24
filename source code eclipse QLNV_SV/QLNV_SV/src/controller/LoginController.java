package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.ConnectionMSSQL;
import DAO.accessNhanVien;
import Security.Ciphers;
import Security.Filter;
import Security.RecaptchaVeritication;

@Controller
public class LoginController {
	private static int turn = 3;
	private HashMap<String, Integer> blackList = new HashMap<String, Integer>();
	
	@RequestMapping("login")
	public String index(HttpServletRequest rq) {
		
		String wrongAuthen = "";
		try {
			wrongAuthen = rq.getParameter("hl");
			
			if(wrongAuthen.equals("again")) {
				String recap;
				String uname;
				try {
					uname = rq.getParameter("uname");
					uname = Filter.cleanXSS(uname);
					if(blackList.containsKey(uname)) {
						Integer luot = blackList.get(uname) - 1;
						if(luot <= 0) {
							blackList.replace(uname, 0);
							return "loginFailed";
						}else {
							blackList.replace(uname, luot);
							rq.setAttribute("message", "Login failed Only " + blackList.get(uname) + " Login Attempts Available");
						}
					}else {
						blackList.put(uname, turn-1);
						rq.setAttribute("message", "Login failed Only " + blackList.get(uname) + " Login Attempts Available");
					}
					
					recap = rq.getParameter("recap");
					if(recap.equals("nul")) {
						rq.setAttribute("xm", "Vui lòng xác thực!");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
								
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "login";
	}
	
	@RequestMapping(value = "authentication", method = RequestMethod.POST)
	public String authentication(HttpServletRequest rq, HttpSession ss, @RequestParam("username") String username, 
			@RequestParam("password") String passwd) throws IOException, NoSuchAlgorithmException {
		
		String gRecaptchaRespone;
		boolean verity = false;
		
		try {
			gRecaptchaRespone = rq.getParameter("g-recaptcha-response");
			verity = RecaptchaVeritication.verify(gRecaptchaRespone);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(!verity) {
			
			return "redirect:login.htm?hl=again&uname="+username+"&recap=nul";
		}else {
			//kiểm soát đầu vào
			username = Filter.cleanSQLI(username);
			passwd = Filter.cleanSQLI(passwd);
			
			String passwordHashed = Ciphers.sha1(passwd);
			byte[] password = Ciphers.hex2Byte(passwordHashed.substring(2));
			
			int tonTai = 0;
			Connection ketnoi = ConnectionMSSQL.layKetNoi();
			//String sql = "select * from NHANVIEN where TENDN = '" + username + "' AND MATKHAU = " + passwordHashed;
			String sql = "select * from NHANVIEN where TENDN = ? AND MATKHAU = ?";
			try {
				PreparedStatement ps = ketnoi.prepareStatement(sql);
				ps.setString(1, username);
				ps.setBytes(2, password);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					tonTai = 1;
					 accessNhanVien.MANV = rs.getString("MANV");
					 accessNhanVien.HOTEN = rs.getString("HOTEN");
					 accessNhanVien.EMAIL = rs.getString("EMAIL");
					 accessNhanVien.TENDN = rs.getString("TENDN");
					 
				}
				rs.close();
				ps.close();
				ketnoi.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			if(tonTai == 1) {
				ss.setAttribute("user", username);
				return "redirect:QLNV.htm";
			}else {
				return "redirect:login.htm?hl=again&uname="+username;
			}
		}
		
		
	}
	
	
	@RequestMapping("logout")
	public String logout(HttpSession ss) {
		ss.removeAttribute("user");
		return "redirect:login.htm";
	}
	
	@RequestMapping("changepass")
	public String Changepass(HttpServletRequest rq, HttpSession ss) {
		ss = rq.getSession(); 
		String username = (String) ss.getAttribute("user");
		
		if(username == null) {
			return "redirect:login.htm";
		}
		
		rq.setAttribute("tendnn", username);
		try {
			String er = rq.getParameter("mes");
			if(er.equals("wrongpass")) {
				rq.setAttribute("message", "Incorect Password!");
			}else if(er.equals("weak")) {
				rq.setAttribute("message", "Weak pass");
			}else if(er.equals("notmatches")) {
				rq.setAttribute("message", "Mat khau khong khop");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "changepassword";
	}
	
	@RequestMapping(value = "changepassword", method = RequestMethod.POST)
	public String changepass(HttpServletRequest rq, HttpSession ss) {
		ss = rq.getSession(); 
		String username = (String) ss.getAttribute("user");
		String currentpass = rq.getParameter("currentpassword");
		String newpass = rq.getParameter("newpassword");
		String confirmpass = rq.getParameter("confirmpassword");
		
		System.out.println("username: " + username);
		System.out.println("cur pass: " + currentpass);
		System.out.println("new pass: " + newpass);
		System.out.println("confirm pass: " + confirmpass);
		
		String currentpassHashed = "";
		try {
			currentpassHashed = Ciphers.sha1(currentpass);
		} catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
		}
		byte[] mang = Ciphers.hex2Byte(currentpassHashed.substring(2));
		
		//xác thực user & pass
		int tontai = 0;
		Connection kn = ConnectionMSSQL.layKetNoi();
		String sql = "select * from NHANVIEN where TENDN = ? AND MATKHAU = ?";
		try {
			PreparedStatement ps = kn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setBytes(2, mang);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				tontai = 1;
			}
			rs.close();
			ps.close();
			kn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		if(tontai == 0) {
			return "redirect:changepass.htm?mes=wrongpass";
		}
		
		//xác thực thành công thì bắt đầu đổi pass
		//kiểm tra độ mạnh của mật khẩu
		if(newpass.equals("") || !newpass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
			return "redirect:changepass.htm?mes=weak";
		}
		
		if(newpass.equals(confirmpass)) {
			String newpassHashed = "";
			try {
				newpassHashed = Ciphers.sha1(newpass);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			Connection kn2 = ConnectionMSSQL.layKetNoi();
			String sql2 = "update NHANVIEN set MATKHAU = ? where TENDN = ?";
			try {
				PreparedStatement ps2 = kn2.prepareStatement(sql2);
				ps2.setBytes(1, Ciphers.hex2Byte(newpassHashed.substring(2)));
				ps2.setString(2, username);
				ps2.executeUpdate();
				ps2.close();
				kn2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			return "redirect:logout.htm";
		}else {
			return "redirect:changepass.htm?mes=notmatches";
		}
		
	}
}
