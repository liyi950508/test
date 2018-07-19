package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.userselect.UserSelectDao;
import com.help.ActionHelp;
import com.model.Resource;
import com.model.User;
import com.model.UserSelect;
import com.opensymphony.xwork2.ActionSupport;

public class UserSelectAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private UserSelect userSelect;
	private UserSelectDao userSelectDao;
	public UserSelect getUserSelect() {
		return userSelect;
	}
	public void setUserSelect(UserSelect userSelect) {
		this.userSelect = userSelect;
	}
	public UserSelectDao getUserSelectDao() {
		return userSelectDao;
	}
	public void setUserSelectDao(UserSelectDao userSelectDao) {
		this.userSelectDao = userSelectDao;
	}

	private int currentPage=1;//��ǰҳ��
	private int pageSize=7;//ÿҳ��ʾ����
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
	
	//ȡ����Դ�ղ�
    public String DeleteCollentResource() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	//��ȡɾ�����ղ���Դ����
    	String selectId = request.getParameter("selectId");
	    UserSelect userSelect = userSelectDao.findById(Integer.parseInt(selectId));
	    String resourceName = userSelect.getSelectName();
	    //����Դ���ȡ��Ӧ���ղ���Դ���󣬽��ղ�������1
	    Resource resource = userSelectDao.getResource(resourceName);
	    if(resource != null) {
		   	//��Դ�ղ�������1
	    	resource.setCollectNumber(resource.getCollectNumber() - 1);
//		    userSelect.setResource(resource);
		    userSelectDao.update(userSelect);
	    }else {
	    	userSelectDao.delete(userSelect);
	    }
	    userSelectDao.delete(userSelect);
		request.setAttribute("deall", "13");
	    return "DeleteCollentSuccess";		
    }
    
    /**
	 * ��ҳչʾ��Դ
	 * @return
	 * @throws Exception
	 */
	//��Դ��ҳչʾ(ȫ��)
    public String showSeclectByPage() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	User user = (User) request.getSession().getAttribute("user");
    	String userName = user.getUsername();
    	if(currentPage <= 0){
    		currentPage = 1;
    	}
    	List<UserSelect> resource = userSelectDao.getAllResourceByPage(currentPage, pageSize, userName);
    	new ActionHelp().getShowPage(request, userSelectDao.getTotal(pageSize, userName), currentPage, resource);
    	return "listSelectResource";
    }	
}
