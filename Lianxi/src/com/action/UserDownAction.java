package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.userdown.UserDownDao;
import com.help.ActionHelp;
import com.model.User;
import com.model.UserDownload;
import com.opensymphony.xwork2.ActionSupport;

public class UserDownAction extends ActionSupport{
	
	private UserDownDao userDownDao;
	private UserDownload userDown;
	public UserDownDao getUserDownDao() {
		return userDownDao;
	}
	public void setUserDownDao(UserDownDao userDownDao) {
		this.userDownDao = userDownDao;
	}
	public UserDownload getUserDown() {
		return userDown;
	}
	public void setUserDown(UserDownload userDown) {
		this.userDown = userDown;
	}
	
	private int pageSize = 6;//每页显示条数
	private int currentPage=1;//当前页码
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//删除下载的资源
	public String UserDownDelete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userDown_Id = request.getParameter("userDownId");
		int userDown_id = Integer.parseInt(userDown_Id);
		UserDownload userDown = userDownDao.findById(userDown_id);
		userDownDao.delete(userDown);
		request.setAttribute("deall", "16");
		return "userDownDelete";
	}
	
	//资源分页展示(全部)
	public String showDownByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		User userUser = (User) request.getSession().getAttribute("user");
		String userName = userUser.getUsername();
		if(currentPage <= 0)
			currentPage = 1;
		List<UserDownload> resource = userDownDao.getAllResourceByPage(currentPage, pageSize, userName);
		new ActionHelp().getShowPage(request, userDownDao.getTotal(pageSize, userName), currentPage, resource);
		return "listDownResource";
	}
}
