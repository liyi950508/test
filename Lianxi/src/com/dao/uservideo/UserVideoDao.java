package com.dao.uservideo;

import java.util.List;

import com.model.UserVideo;
import com.model.Video;

public interface UserVideoDao {
	void saveUserVideo(UserVideo userVideo);	//添加下载的资源
	UserVideo getUseVideoName(String userVideo);	//按名称查找下载的资源
	Video getVideo(String videoName);	//按名称查找资源
	UserVideo findById(int id);  //按id查找下载的资源
	void delete(UserVideo userVideo);
	void update(UserVideo userVideo);	//更新下载的资源
	List<UserVideo> findAll();	//查找所有下载的资源
	//	分页查询所有下载的资源

	List<UserVideo> getAllResourceByPage(int currentPage, int pageSize,
			String userName, String videoType, String down) throws Exception;
	//	查询所有下载的资源的总条数计算总页数
	int getTotal(int pageSize, String userName, String videoType, String down)
			throws Exception;
}
