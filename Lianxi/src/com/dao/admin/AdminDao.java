package com.dao.admin;

import java.util.List;

import com.model.Admin;

public interface AdminDao {
	void save(Admin admin);	//��ӹ���Ա
	Admin getAdmin(String adminName);	//�����Ʋ��ҹ���Ա
	void delete(int adminId);	//��idɾ������Ա
	void update(Admin admin);	//���¹���Ա
	Admin findById(int adminId);  //��id���ҹ���Ա
	List<Admin> findAll();	//�������й���Ա
	Admin login(String adminName, String adminPassword);  //����Ա��¼
}
