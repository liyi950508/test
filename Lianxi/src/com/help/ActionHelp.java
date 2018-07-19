package com.help;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.model.User;

public class ActionHelp {
	/**
	 * action��ҳ��������
	 * @param <T>
	 * @param request
	 * @param totalPage
	 * @param resource
	 */
	public <T> void getShowPage(HttpServletRequest request, int totalPage, int currentPage,
			List<T> resource) {
		//ʵ����PageBean,����currentPage��totalPage����pageBean
		PageBean<T> pageBean = new PageBean<T>(currentPage, totalPage);
		Help help = new Help();
		request.setAttribute("help", help);
		request.setAttribute("pageBean", pageBean);
		//�洢��ʾ����
		request.setAttribute("resource", resource);
	}
	
	//��ȡ��ǰ�û�
	public String getUserName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		String userName = user.getUsername();
		return userName;
	}
	
	//����������ҳ������
	public int showPublicByPage(int pageSize, int totalSize) {
		int totalPage;
		int mod=totalSize%pageSize;
		if(mod==0){
			totalPage=totalSize/pageSize;
	    }else{
		    totalPage=totalSize/pageSize+1;
		}
		return totalPage;
	}

	// ����ʱ���
	public int getDateNum(Date dateTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		long days = 0;
		try {
			Date date = df.parse(date2 + " " + "00:00:00");
			Date date1 = df.parse(dateTime + " " + "00:00:00");

			long diff = date.getTime() - date1.getTime();
			days = diff / (1000 * 60 * 60 * 24);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) days;
	}

}
