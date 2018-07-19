package com.dao.userdown;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.UserDownload;

public class UserDownDaoImpl extends HibernateDaoSupport implements UserDownDao {

	public void save(UserDownload userDown) {
		this.getHibernateTemplate().save(userDown);
	}

	public void delete(UserDownload userDown) {
		this.getHibernateTemplate().delete(userDown);
	}

	public void update(UserDownload userDown) {
		this.getHibernateTemplate().update(userDown);
	}

	@SuppressWarnings("unchecked")
	public UserDownload getUserDownName(String userDownName) {
		String sql = "from UserDownload where resourceName='"+userDownName+"'";
		List<UserDownload> resource = this.getHibernateTemplate().find(sql);
		if(resource != null && resource.size() > 0) {
			return resource.get(0);
		}
		return null;
	}

	public UserDownload findById(int id) {
		UserDownload resource = this.getHibernateTemplate().get(UserDownload.class, id);
		return resource;
	}

	@SuppressWarnings("unchecked")
	public List<UserDownload> findAll() {
		String sql = "from UserDownload";
		List<UserDownload> list = this.getHibernateTemplate().find(sql);
		return list;
	}

	/**
	 * 分页查询
	 * 展示资源
	 */
	public List<UserDownload> getAllResourceByPage(int currentPage, int pageSize, String userName)
			throws Exception {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		String sql = "from UserDownload";
		List<UserDownload> userDown = showByPage(currentPage, pageSize, session, tx, sql);
		return userDown;
	}

	public int getTotal(int pageSize, String userName) throws Exception {
		int totalPage = 0;
		String sql = "from UserDownload";
		totalPage = getTotalPage(pageSize, sql);
		return totalPage;
	}

	/**
	 * 分页和总页数计算
	 * 基础方法
	 */
	//分页算法
	@SuppressWarnings("unchecked")
	public List<UserDownload> showByPage(int currentPage, int pageSize,
			Session session, Transaction tx, String sql) {
		 Query query=session.createQuery(sql);  
	     //进行分页setFirstResult为起始记录数(currentPage-1)*pageSize算法
		 int startRow=(currentPage-1)*pageSize; 
		 query.setFirstResult(startRow);   
		 //每页显示记录数
		 query.setMaxResults(pageSize);  
		 List<UserDownload> resource=query.list();
		 tx.commit();
		 session.close();
		 return resource;
	}
	//总页数算法
	@SuppressWarnings("unchecked")
	public int getTotalPage(int pageSize, String sql) {
		int totalPage;
		List<UserDownload> list = this.getHibernateTemplate().find(sql);  
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
