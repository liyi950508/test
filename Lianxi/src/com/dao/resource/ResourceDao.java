package com.dao.resource;

import java.util.List;

import com.model.Resource;

public interface ResourceDao {
	void save(Resource resource);	//�����Դ
	Resource getResource(String resourceName, String uploadUser, 
			String label, String category);	//�����Ʋ�����Դ
	Resource findById(int id);  //��id�����û�
	void delete(Resource resource);
	void update(Resource resourceName);	//������Դ
	List<Resource> findAll();	//����������Դ
	//	��ҳ��ѯ��Դ

	List<Resource> getAllResourceByPage(int currentPage, int pageSize,
			String label, String isExamine, String orderBy) throws Exception;
	
	//	���ֶη�ҳ��ѯ��Դ
	List<Resource> getResourceByPage(int currentPage, int pageSize, String resourceName, String uploadUser, String label, String category,String type, String orderBy) throws Exception;
	//	��ѯ��Դ��������������ҳ��
	int getAllTotal(int pageSize, String label, String isExamine)
			throws Exception;
	int getTotal(int pageSize, String resourceName, String uploadUser, String label, String category, String type) throws Exception;

	// ������ѯ����
	public List<Resource> searchResByPage(int currentPage, int pageSize,
			String search);
	public int searchResTotal(int pageSize, String search);
}

