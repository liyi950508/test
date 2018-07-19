package com.dao.user;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	
	//用户登录
	@SuppressWarnings("unchecked")
	public User login(String username, String password) {
		String hql = "from User u where u.username ='"+username+"' and u.password ='"+password+"'";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	//获取最大id号用户
	@SuppressWarnings("unchecked")
	public User getMaxIdUser() {
		String sql = "from User where id = (select max(id) from User)";
		List<User> maxId = this.getHibernateTemplate().find(sql);
		if(maxId != null && maxId.size() > 0){
			return maxId.get(0); 
		}
		return null;
	}
	
	//添加用户
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}
	//按用户名查找用户
	@SuppressWarnings("unchecked")
	public User getUser(String username) {
		String hsql = "from User u where u.username='" +username+ "'";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hsql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	//删除用户
	public void delete(User user) {
		this.getHibernateTemplate().delete(user);
	}

	public void update(User user) {
		this.getHibernateTemplate().update(user);
	}

	public User findById(int id) {
		User user = this.getHibernateTemplate().get(User.class, id);
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		String hsql = "from User";
		List<User> list = this.getHibernateTemplate().find(hsql);
		return list;
	}
	
	/**
	 * 分页查询
	 * 展示用户
	 */
	//分页列出所有用户
	public List<User> getAllUserByPage(int currentPage, int pageSize,
			String username, String userJob)
			throws Exception {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		String sql = null;
		if (username == null && userJob == null) {
			sql = "from User";
		} else if (username != null && userJob == null) {
			sql = "from User u where u.username='" + username + "'";
		} else if (username == null && userJob != null) {
			sql = "from User u where u.userJob='" + userJob + "'";
		} else if (username != null && userJob != null) {
			sql = "from User u where u.username='" + username
					+ "' and u.userJob='" + userJob + "'";
		}

		List<User> user = showByPage(currentPage, pageSize, session, tx, sql);
		return user;
		
	}
	//查询总记录数并计算总页数
	public int getAllTotal(int pageSize, String username, String userJob)
			throws Exception {
		 //初始化总页数
	      int totalPage = 0;
		String sql = null;
		if (username == null && userJob == null) {
			sql = "from User";
		} else if (username != null && userJob == null) {
			sql = "from User u where u.username='" + username + "'";
		} else if (username == null && userJob != null) {
			sql = "from User u where u.userJob='" + userJob + "'";
		} else if (username != null && userJob != null) {
			sql = "from User u where u.username='" + username
					+ "' and u.userJob='" + userJob + "'";
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
	public List<User> showByPage(int currentPage, int pageSize,
			Session session, Transaction tx, String sql) {
		 Query query=session.createQuery(sql);  
	     //进行分页setFirstResult为起始记录数(currentPage-1)*pageSize算法
		 int startRow=(currentPage-1)*pageSize; 
		 query.setFirstResult(startRow);   
		 //每页显示记录数
		 query.setMaxResults(pageSize);  
		 List<User> resource=query.list();
		 tx.commit();
		 session.close();
		 return resource;
	}
	//总页数算法
	@SuppressWarnings("unchecked")
	public int getTotalPage(int pageSize, String sql) {
		int totalPage;
		List<User> list = this.getHibernateTemplate().find(sql);  
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
