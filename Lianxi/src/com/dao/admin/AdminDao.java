package com.dao.admin;

import java.util.List;

import com.model.Admin;

public interface AdminDao {
	void save(Admin admin);	//添加管理员
	Admin getAdmin(String adminName);	//按名称查找管理员
	void delete(int adminId);	//按id删除管理员
	void update(Admin admin);	//更新管理员
	Admin findById(int adminId);  //按id查找管理员
	List<Admin> findAll();	//查找所有管理员
	Admin login(String adminName, String adminPassword);  //管理员登录
}
