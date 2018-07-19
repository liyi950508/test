package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.resource.ResourceDao;
import com.dao.shequ.SheQuDao;
import com.dao.video.VideoDao;
import com.help.ActionHelp;
import com.model.Question;

public class SearchAction {

	private SheQuDao sheQuDao;
	private VideoDao videoDao;
	private ResourceDao resourceDao;
	public SheQuDao getSheQuDao() {
		return sheQuDao;
	}
	public void setSheQuDao(SheQuDao sheQuDao) {
		this.sheQuDao = sheQuDao;
	}
	public VideoDao getVideoDao() {
		return videoDao;
	}
	public void setVideoDao(VideoDao videoDao) {
		this.videoDao = videoDao;
	}
	public ResourceDao getResourceDao() {
		return resourceDao;
	}
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	private int currentPage = 1;// ��ǰҳ��
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	// ������ҳ��ѯ����
	public String ShowQuestionByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();

		String search = new String(request.getParameter("search").getBytes(
				"ISO8859-1"), "UTF-8");
		// �����ǰҳ��С��1����ֵΪ1������ҳ
		if (currentPage <= 0) {
			currentPage = 1;
		}
		// ��ȡ��ҳ��
		int totalPage = sheQuDao.searchQuestionTotal(10, search);
		// ��ҳ��ѯ����
		List<Question> resource = sheQuDao.searchQuestionByPage(currentPage,
				10, search);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "showQuestion";
	}
}
