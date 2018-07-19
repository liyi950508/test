package com.dao.userdown;

import java.util.List;

import com.model.UserDownload;

public interface UserDownDao {
	void save(UserDownload userDown);	//������ص���Դ
	UserDownload getUserDownName(String userDownName);	//�����Ʋ������ص���Դ
	UserDownload findById(int id);  //��id�������ص���Դ
	void delete(UserDownload userDown);
	void update(UserDownload userDown);	//�������ص���Դ
	List<UserDownload> findAll();	//�����������ص���Դ
	//	��ҳ��ѯ�������ص���Դ
	List<UserDownload> getAllResourceByPage(int currentPage, int pageSize, String userName) throws Exception;
	//	��ѯ�������ص���Դ��������������ҳ��
	int getTotal(int pageSize, String userName) throws Exception;
	
}
