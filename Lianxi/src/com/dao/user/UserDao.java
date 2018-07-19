package com.dao.user;

import java.util.List;

import com.model.User;

public interface UserDao {
	void save(User user);	//添加用户
	User getUser(String username);	//按用户名查找用户
	void delete(User user);	//删除用户
	void update(User user);	//更新用户
	User findById(int id);  //按id查找用户
	List<User> findAll();	//查找所有用户
	User getMaxIdUser();  //获取最大id号用户
	User login(String username, String password);  //用户登录
	//分页展示所有资源

	List<User> getAllUserByPage(int currentPage, int pageSize, String username,
			String userJob) throws Exception;
	//查询总记录数并计算总页数
	int getAllTotal(int pageSize, String username, String userJob)
			throws Exception;

}
