package com.dao.userdown;

import java.util.List;

import com.model.UserDownload;

public interface UserDownDao {
	void save(UserDownload userDown);	//添加下载的资源
	UserDownload getUserDownName(String userDownName);	//按名称查找下载的资源
	UserDownload findById(int id);  //按id查找下载的资源
	void delete(UserDownload userDown);
	void update(UserDownload userDown);	//更新下载的资源
	List<UserDownload> findAll();	//查找所有下载的资源
	//	分页查询所有下载的资源
	List<UserDownload> getAllResourceByPage(int currentPage, int pageSize, String userName) throws Exception;
	//	查询所有下载的资源的总条数计算总页数
	int getTotal(int pageSize, String userName) throws Exception;
	
}
