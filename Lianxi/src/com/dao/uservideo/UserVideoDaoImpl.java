package com.dao.uservideo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.help.Help;
import com.model.UserVideo;
import com.model.Video;

public class UserVideoDaoImpl extends HibernateDaoSupport implements UserVideoDao {

	public void saveUserVideo(UserVideo userVideo) {
		this.getHibernateTemplate().save(userVideo);
	}

	public void delete(UserVideo userVideo) {
		this.getHibernateTemplate().delete(userVideo);
	}

	public void update(UserVideo userVideo) {
		this.getHibernateTemplate().update(userVideo);
	}
	
	@SuppressWarnings("unchecked")
	public UserVideo getUseVideoName(String userVideo) {
		String sql = "from UserVideo where videoName='"+userVideo+"'";
		List<UserVideo> list = this.getHibernateTemplate().find(sql);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Video getVideo(String videoName) {
		String hsql = "from Video v where v.videoName='" +videoName+ "'";
		List<Video> list = (List<Video>)this.getHibernateTemplate().find(hsql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public UserVideo findById(int id) {
		UserVideo resource = this.getHibernateTemplate().get(UserVideo.class, id);
		return resource;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<UserVideo> findAll() {
		String sql = "from UserVideo";
		List<UserVideo> list = this.getHibernateTemplate().find(sql);
		return list;
	}

	@Override
	public List<UserVideo> getAllResourceByPage(int currentPage, int pageSize,
			String userName, String videoType, String down) throws Exception {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		String sql = null;
		if (down == null) {
			if (videoType == null || videoType.equals(new Help().getStudy_0())) {
			sql = "from UserVideo v where v.videoUser='"+userName+"' and v.study='"+new Help().getStudy_0()+"'";
		}else{
			if(videoType.equals(new Help().getStudy_1())){
				sql = "from UserVideo u where u.videoUser='"+userName+"' and u.study='"+new Help().getStudy_1()+"'";
			}
			if(videoType.equals(new Help().getCollent())){
				sql = "from UserVideo u where u.videoUser='"+userName+"' and u.collent='"+new Help().getCollent()+"'";
			}
		}
		} else {
			sql = "from UserVideo v where u.videoUser='" + userName
					+ "' and v.download='" + down + "'";
		}
		 
		List<UserVideo> userVideo = showByPage(currentPage, pageSize, session, tx, sql);
		return userVideo;
	}

	@Override
	public int getTotal(int pageSize, String userName, String videoType,
			String down)
			throws Exception {
		int totalPage = 0;
		String sql = null;
		if (down == null) {
			if (videoType == null || videoType.equals(new Help().getStudy_0())) {
				sql = "from UserVideo v where v.videoUser='" + userName
						+ "' and v.study='" + new Help().getStudy_0() + "'";
			} else {
				if (videoType.equals(new Help().getStudy_1())) {
					sql = "from UserVideo u where u.videoUser='" + userName
							+ "' and u.study='" + new Help().getStudy_1() + "'";
				}
				if (videoType.equals(new Help().getCollent())) {
					sql = "from UserVideo u where u.videoUser='" + userName
							+ "' and u.collent='" + new Help().getCollent()
							+ "'";
				}
			}
		}else{
			sql = "from UserVideo v where u.videoUser='" + userName
					+ "' and v.download='" + down + "'";
		}

		totalPage = getTotalPage(pageSize, sql);
		return totalPage;
	}

	/**
	 * 分页和总页数计算
	 * 基础方法
	 */
	//分页算法
	@SuppressWarnings("unchecked")
	public List<UserVideo> showByPage(int currentPage, int pageSize,
			Session session, Transaction tx, String sql) {
		 Query query=session.createQuery(sql);  
	     //进行分页setFirstResult为起始记录数(currentPage-1)*pageSize算法
		 int startRow=(currentPage-1)*pageSize; 
		 query.setFirstResult(startRow);   
		 //每页显示记录数
		 query.setMaxResults(pageSize);  
		 List<UserVideo> resource=query.list();
		 tx.commit();
		 session.close();
		 return resource;
	}
	//总页数算法
	@SuppressWarnings("unchecked")
	public int getTotalPage(int pageSize, String sql) {
		int totalPage;
		List<UserVideo> list = this.getHibernateTemplate().find(sql);  
		  //得到总记录数
		  int totalSize = list.size();
		  //计算总页数
		  int mod=totalSize%pageSize;
		  if(mod==0){
			  totalPage=totalSize/pageSize;
		  }else{
			  totalPage=totalSize/pageSize+1;
		  }
		return totalPage;
	}
	
}
