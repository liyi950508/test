package com.action;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.dao.user.UserDao;
import com.help.ActionHelp;
import com.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = -5898839400788046536L;

	private UserDao userDao;
	private User user;

	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	private int pageSize = 6;//ÿҳ��ʾ����
	private int currentPage=1;//��ǰҳ��
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

	//�û�ע��
	public String UserRegister() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//��ҳ���ȡ��Ϣ
		String username = request.getParameter("user.username");
		//����username��ѯ���ݿ�
		User userName = userDao.getUser(username);

		if(user.getUsername()==null || "".equals(user.getUsername())){
			request.setAttribute("deal", "1");
			return "dealWith";
		}
		else if(user.getUsername().length()>15 || user.getUsername().length()<2){
			request.setAttribute("deal", "2");
			return "dealWith";
		}
		else if(user.getPassword()==null || "".equals(user.getPassword())) {
			request.setAttribute("deal", "1");
			return "dealWith";
		}	
		else if(user.getPassword().length()>12 || user.getPassword().length()<6) {
			request.setAttribute("deal", "3");
			return "dealWith";
		}	
		else if(userName != null) {
			request.setAttribute("deal", "4");
			return "dealWith";
		}
		else {
			user.setIntegral(30);
			userDao.save(user);
			request.setAttribute("deal", "5");
			return "dealWith";
		}
	}

	//	�û���¼
	public String userLogin() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//��ȡ��ǰ�û�
		User userUser = (User) request.getSession().getAttribute("user");
		//��ҳ���ȡ��Ϣ
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userDao.getUser(username);
		if (user.getUsername() != null) {
			if (userUser == null) {
				if (username.isEmpty() || password.isEmpty()) {
					request.setAttribute("deal", "8");
					return "loginError";
				} else {
					if (password.equals(user.getPassword())) {
						request.getSession().setAttribute("user", user);
						request.setAttribute("deal", "6");
						return "loginSuccess";
					} else {
						request.setAttribute("deal", "8");
						return "loginError";
					}
				}
			} else {// �û��ѵ�¼
				request.setAttribute("deal", "9");
				return "loginUserError";
			}
		} else {
			request.setAttribute("deal", "7");
			return "dealWith";
		}

	}
	
	//�˳���¼
	public String userExit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().invalidate();
		return "userexit";
	}
	
	//�ж��û��Ƿ�����
	public boolean isLoginUser() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		User userUser = (User) request.getSession().getAttribute("user");
		request.setAttribute("user", userUser);
		if(userUser == null) {
			return false;
		}else
			return true;
	}
	
	// ����Ա����û�
	public void addUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��ҳ���ȡ��Ϣ
		String username = request.getParameter("user.username");

		User userUser = userDao.getUser(username);
		if (userUser == null) {
			user.setIntegral(30);
			userDao.save(user);
		}
	}

	//�û��޸�����
	public String UserUpDatePassword() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		User userUser = (User) request.getSession().getAttribute("user");
		String userPassword = userUser.getPassword() ;
		String password = request.getParameter("password");
		if(password.equals(userPassword)) {
			String newPassword1 = request.getParameter("newPassword1");
			userUser.setPassword(newPassword1);
			userDao.update(userUser);
			request.setAttribute("deal", "10");
			return "updatePwdSuccess";
		} else {
			request.setAttribute("deal", "11");
			return "passwordError";
		
		}

	}
	
	//��id��ɾ���û�
	public String AdminUserDelete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userId = request.getParameter("userId");
		Integer userID = Integer.valueOf(userId);
		User user = userDao.findById(userID);
		userDao.delete(user);	
		return "userDelete";
	}
	
	//�����û���Ϣ
	public String UserUpDate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = userDao.findById(Integer.valueOf(request
				.getParameter("userId")));
		user.setUsername(request.getParameter("username"));
		user.setUserJob(request.getParameter("userJob"));
		userDao.update(user);
		return "userUpDate";
	}
	public String adminUserUpDate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = userDao.findById(Integer.parseInt(request
				.getParameter("userId")));
		user.setUsername(request.getParameter("username"));
		user.setIntegral(Integer.valueOf(request.getParameter("integral")));
		user.setPassword(request.getParameter("password"));
		user.setUserJob(request.getParameter("userJob"));
		userDao.update(user);
		return "userUpDate";

	}
	//�û���Ϣ�༭
	public String UserEdit() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("user", user);
		return "userEdit";
	}
	public void adminUserEdit() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		User user = userDao.findById(Integer.valueOf(request
				.getParameter("userId")));
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/admin/user/user_Edit.jsp");
		dispatcher.forward(request, response);
	}
	
	//��ҳ�г������û�
	public String showAllUserByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();

		String username = null;
		String userJob = null;
		username = request.getParameter("username");
		userJob = request.getParameter("userJob");

		//�����ǰҳ��С��1����ֵΪ1������ҳ
		if(currentPage < 1) {
			 currentPage = 1;
		}
		//��ȡ��ҳ��
		int totalPage = userDao.getAllTotal(pageSize, username, userJob);
		//��ҳ��ѯ����
		List<User> user = userDao.getAllUserByPage(currentPage, pageSize,
				username, userJob);
		new ActionHelp().getShowPage(request, totalPage, currentPage, user);
		return "listAllUser";
	}
	
}
