package com.dao.user;

import java.util.List;

import com.model.User;

public interface UserDao {
	void save(User user);	//����û�
	User getUser(String username);	//���û��������û�
	void delete(User user);	//ɾ���û�
	void update(User user);	//�����û�
	User findById(int id);  //��id�����û�
	List<User> findAll();	//���������û�
	User getMaxIdUser();  //��ȡ���id���û�
	User login(String username, String password);  //�û���¼
	//��ҳչʾ������Դ

	List<User> getAllUserByPage(int currentPage, int pageSize, String username,
			String userJob) throws Exception;
	//��ѯ�ܼ�¼����������ҳ��
	int getAllTotal(int pageSize, String username, String userJob)
			throws Exception;

}
