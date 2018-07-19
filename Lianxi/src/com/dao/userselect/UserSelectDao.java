package com.dao.userselect;

import java.util.List;

import com.model.Resource;
import com.model.UserSelect;

public interface UserSelectDao {
	void saveUserSelect(UserSelect userSelect);	//������ص���Դ
	UserSelect getUserSelectName(String userSelect);	//�����Ʋ������ص���Դ
	Resource getResource(String resourceName);	//�����Ʋ�����Դ
	UserSelect findById(int id);  //��id�������ص���Դ
	void delete(UserSelect userSelect);
	void update(UserSelect userSelect);	//�������ص���Դ
	List<UserSelect> findAll();	//�����������ص���Դ
	//	��ҳ��ѯ�������ص���Դ
	List<UserSelect> getAllResourceByPage(int currentPage, int pageSize, String userName) throws Exception;
	//	��ѯ�������ص���Դ��������������ҳ��
	int getTotal(int pageSize, String userName) throws Exception;
}
