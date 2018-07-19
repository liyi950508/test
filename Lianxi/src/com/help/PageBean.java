package com.help;

import java.util.List;

public class PageBean<T> {
	private int currentPage;//��ǰҳ��
	private int totalPage;//��ҳ��
	private int totalSize;//������
	private int pageSize;//ÿҳ��ʾ����
	private List<T> resourceList;//��ҳ��ʾ����
	private int forEachBegin;//jspҳ��foreach��ǩѭ���Ŀ�ʼҳ��
	private int forEachEnd;//jspҳ��foreach��ǩѭ���Ľ���ҳ��
    
	public PageBean() {
    	super();
    }
	
	public void getPage() {
		if(currentPage <= 0) {
			currentPage = 1;
		}
	}
	
    public PageBean(int currentPage, int totalPage) {
		super();
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		
		if(totalPage <= 5){
			forEachBegin = 1;
			forEachEnd = totalPage;
		}else{
			forEachBegin = currentPage - 2;
			forEachEnd = currentPage + 2;
			if(forEachBegin <= 1) {
				forEachBegin = 1;
				forEachEnd = 5;
			}
			if(forEachEnd >= totalPage) {
				forEachBegin = totalPage - 4;
				forEachEnd = totalPage;
			}
		}
	}
	public List<T> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<T> resourceList) {
		this.resourceList = resourceList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getForEachBegin() {
		return forEachBegin;
	}
	public void setForEachBegin(int forEachBegin) {
		this.forEachBegin = forEachBegin;
	}
	public int getForEachEnd() {
		return forEachEnd;
	}
	public void setForEachEnd(int forEachEnd) {
		this.forEachEnd = forEachEnd;
	}
    
}
