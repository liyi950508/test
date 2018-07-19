package com.dao.userselect;

import java.util.List;

import com.model.Resource;
import com.model.UserSelect;

public interface UserSelectDao {
	void saveUserSelect(UserSelect userSelect);	//添加下载的资源
	UserSelect getUserSelectName(String userSelect);	//按名称查找下载的资源
	Resource getResource(String resourceName);	//按名称查找资源
	UserSelect findById(int id);  //按id查找下载的资源
	void delete(UserSelect userSelect);
	void update(UserSelect userSelect);	//更新下载的资源
	List<UserSelect> findAll();	//查找所有下载的资源
	//	分页查询所有下载的资源
	List<UserSelect> getAllResourceByPage(int currentPage, int pageSize, String userName) throws Exception;
	//	查询所有下载的资源的总条数计算总页数
	int getTotal(int pageSize, String userName) throws Exception;
}
