package com.dao.video;

import java.util.List;

import com.model.UserVideo;
import com.model.Video;

public interface VideoDao {
	void add(Video video);    //�����Ƶ
	void delete(Video video); //ɾ����Ƶ
	void update(Video video); //������Ƶ
	List<Video> findAll();    //����������Ƶ
	Video findById( int id);		  //��id�Ų�����Ƶ 
	
	UserVideo getUserVideo(String user, String videoName); //���û�������Դ����ѯ�û���Ƶ��
	
	Video findByName(String videoName, String videoLabel, String videoAuthor, String category);//�����Ʋ�����Ƶ
	Video findByAuthor(String videoAuthor);

	// ������ѯ����
	public List<Video> searchVideoByPage(int currentPage, int pageSize,
			String search);

	public int searchVideoTotal(int pageSize, String search);

	//	��ҳ��ѯ������Ƶ
	List<Video> getAllVideoByPage(int currentPage, int pageSize, String label, String category, String orderBy) throws Exception;
	//	��ѯ������Ƶ��������������ҳ��
	int getVideoTotal(int pageSize, String label, String category) throws Exception;
}
