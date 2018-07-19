package com.action;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.dao.uservideo.UserVideoDao;
import com.help.ActionHelp;
import com.help.Help;
import com.help.PageBean;
import com.model.User;
import com.model.UserVideo;
import com.opensymphony.xwork2.ActionSupport;

public class USerVideoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private UserVideo userVideo;
	private UserVideoDao userVideoDao;
	public UserVideo getUserVideo() {
		return userVideo;
	}
	public void setUserVideo(UserVideo userVideo) {
		this.userVideo = userVideo;
	}
	public UserVideoDao getUserVideoDao() {
		return userVideoDao;
	}
	public void setUserVideoDao(UserVideoDao userVideoDao) {
		this.userVideoDao = userVideoDao;
	}

	private int currentPage=1;//当前页码
	private int pageSize=8;//每页显示条数
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

	// 删除学习的视频
	public String DeleteStudyVideo() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	UserVideo userVideo = userVideoDao.findById(Integer.parseInt(request.getParameter("userVideoId")));
		String type = new String(request.getParameter("type").getBytes(
				"ISO8859-1"), "UTF-8");
		System.out.println(type);
		if (type.equals("收藏")) {
			userVideo.setCollent(null);
			userVideoDao.update(userVideo);
			request.setAttribute("deall", "13");
			return "DeleteCollentSuccess";
		} else if (type.equals("在学") || type.equals(new Help().getStudy_1())) {
			userVideo.setStudy(null);
			userVideoDao.update(userVideo);
			request.setAttribute("deall", "13");
			return "DeleteStudyVideo";
		} else {
			userVideo.setDownload(null);
			userVideoDao.update(userVideo);
			request.setAttribute("deall", "13");
			return "DeleteDownVideo";
		}
    }
    
    /**
	 * 分页展示资源
	 * @return
	 * @throws Exception
	 */
	//资源分页展示(全部)
    public String showSeclectByPage() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	User user = (User) request.getSession().getAttribute("user");
    	if(user == null){
    		return "BackLogin";
    	} else{
    		String userName = user.getUsername();
	    	String type = null;
	    	if(request.getParameter("fileType") != null){
	    		type = new String(request.getParameter("fileType").getBytes("ISO8859-1"), "UTF-8");
	    		request.setAttribute("type", type);
	    	}else{
	    		request.setAttribute("type", new Help().getStudy_0());
	    	}
	    	if(currentPage <= 0){
	    		currentPage = 1;
	    	}
			int totalSize = userVideoDao.getTotal(pageSize, userName, type,
					null);
			List<UserVideo> resource = userVideoDao.getAllResourceByPage(
					currentPage, pageSize, userName, type, null);
	    	new ActionHelp().getShowPage(request, totalSize, currentPage, resource);
	    	return "listSelectVideo";
    	}
	}

	public void showDownBypage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = (User) request.getSession().getAttribute("user");
    	
		String userName = user.getUsername();
		String down = new String(request.getParameter("down").getBytes(
				"ISO8859-1"), "UTF-8");
		request.setAttribute("type", "视频课程");

		if (currentPage <= 0) {
			currentPage = 1;
		}
		int totalSize = userVideoDao.getTotal(pageSize, userName, null, down);
		List<UserVideo> video = userVideoDao.getAllResourceByPage(
				currentPage, pageSize, userName, null, down);
		PageBean<UserVideo> pageBean = new PageBean<UserVideo>(currentPage,
				totalSize);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("video", video);
		RequestDispatcher dis = request
				.getRequestDispatcher("jsp/personal/My_Download.jsp");
		dis.forward(request, response);
	}
}
