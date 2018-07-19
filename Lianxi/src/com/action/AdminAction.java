package com.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.admin.AdminDao;
import com.model.Admin;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport {
	private static final long serialVersionUID = 3984882672145250035L;
	
	private Admin admin;
	private AdminDao adminDao;
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public AdminDao getAdminDao() {
		return adminDao;
	}
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	//	管理员登录
	public String adminLogin() {
		HttpServletRequest request = ServletActionContext.getRequest();	
		//从页面获取信息
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");
		
		if(adminName.isEmpty() || adminPassword.isEmpty()) {
			return "loginError"; 
		}else {
			Admin admin = adminDao.login(adminName, adminPassword);
			if(admin != null){
				request.getSession().setAttribute("admin", admin);
				return "adminLoginSuccess";
			}else{
				return "adminLoginError";
			}
		}
	}
	
	//退出登录
	public String userExit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().invalidate();
		return "userexit";
	}
		
}
