package com.dao.uservideo;

import java.util.List;

import com.model.UserVideo;
import com.model.Video;

public interface UserVideoDao {
	void saveUserVideo(UserVideo userVideo);	//������ص���Դ
	UserVideo getUseVideoName(String userVideo);	//�����Ʋ������ص���Դ
	Video getVideo(String videoName);	//�����Ʋ�����Դ
	UserVideo findById(int id);  //��id�������ص���Դ
	void delete(UserVideo userVideo);
	void update(UserVideo userVideo);	//�������ص���Դ
	List<UserVideo> findAll();	//�����������ص���Դ
	//	��ҳ��ѯ�������ص���Դ

	List<UserVideo> getAllResourceByPage(int currentPage, int pageSize,
			String userName, String videoType, String down) throws Exception;
	//	��ѯ�������ص���Դ��������������ҳ��
	int getTotal(int pageSize, String userName, String videoType, String down)
			throws Exception;
}
