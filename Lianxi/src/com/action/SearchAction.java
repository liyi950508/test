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

	private int currentPage = 1;// 当前页码
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	// 搜索分页查询问题
	public String ShowQuestionByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();

		String search = new String(request.getParameter("search").getBytes(
				"ISO8859-1"), "UTF-8");
		// 如果当前页码小于1，则赋值为1，即首页
		if (currentPage <= 0) {
			currentPage = 1;
		}
		// 获取总页数
		int totalPage = sheQuDao.searchQuestionTotal(10, search);
		// 分页查询数据
		List<Question> resource = sheQuDao.searchQuestionByPage(currentPage,
				10, search);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "showQuestion";
	}
}
