package com.dao.video;

import java.util.List;

import com.model.UserVideo;
import com.model.Video;

public interface VideoDao {
	void add(Video video);    //添加视频
	void delete(Video video); //删除视频
	void update(Video video); //跟新视频
	List<Video> findAll();    //查找所有视频
	Video findById( int id);		  //按id号查找视频 
	
	UserVideo getUserVideo(String user, String videoName); //按用户名和资源名查询用户视频表
	
	Video findByName(String videoName, String videoLabel, String videoAuthor, String category);//按名称查找视频
	Video findByAuthor(String videoAuthor);

	// 搜索查询问题
	public List<Video> searchVideoByPage(int currentPage, int pageSize,
			String search);

	public int searchVideoTotal(int pageSize, String search);

	//	分页查询所有视频
	List<Video> getAllVideoByPage(int currentPage, int pageSize, String label, String category, String orderBy) throws Exception;
	//	查询所有视频的总条数计算总页数
	int getVideoTotal(int pageSize, String label, String category) throws Exception;
}
