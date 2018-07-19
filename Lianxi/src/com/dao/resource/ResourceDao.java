package com.dao.resource;

import java.util.List;

import com.model.Resource;

public interface ResourceDao {
	void save(Resource resource);	//添加资源
	Resource getResource(String resourceName, String uploadUser, 
			String label, String category);	//按名称查找资源
	Resource findById(int id);  //按id查找用户
	void delete(Resource resource);
	void update(Resource resourceName);	//更新资源
	List<Resource> findAll();	//查找所有资源
	//	分页查询资源

	List<Resource> getAllResourceByPage(int currentPage, int pageSize,
			String label, String isExamine, String orderBy) throws Exception;
	
	//	按字段分页查询资源
	List<Resource> getResourceByPage(int currentPage, int pageSize, String resourceName, String uploadUser, String label, String category,String type, String orderBy) throws Exception;
	//	查询资源的总条数计算总页数
	int getAllTotal(int pageSize, String label, String isExamine)
			throws Exception;
	int getTotal(int pageSize, String resourceName, String uploadUser, String label, String category, String type) throws Exception;

	// 搜索查询问题
	public List<Resource> searchResByPage(int currentPage, int pageSize,
			String search);
	public int searchResTotal(int pageSize, String search);
}

