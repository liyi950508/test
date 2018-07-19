package com.dao.userselect;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.Resource;
import com.model.UserSelect;

public class UserSelectDaoImpl extends HibernateDaoSupport implements UserSelectDao {
	
	public void saveUserSelect(UserSelect userSelect) {
		this.getHibernateTemplate().save(userSelect);
	}

	public void delete(UserSelect userSelect) {
		this.getHibernateTemplate().delete(userSelect);
	}

	public void update(UserSelect userSelect) {
		this.getHibernateTemplate().update(userSelect);
	}

	//按名称查询资源表
	@SuppressWarnings("unchecked")
	public Resource getResource(String resourceName){
		String hsql = "from Resource r where r.resourceName='" +resourceName+ "'";
		List<Resource> list = (List<Resource>)this.getHibernateTemplate().find(hsql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public UserSelect getUserSelectName(String userSelect) {
		String sql = "from UserSelect where resourceName='"+userSelect+"'";
		List<UserSelect> list = this.getHibernateTemplate().find(sql);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public UserSelect findById(int id) {
		UserSelect resource = this.getHibernateTemplate().get(UserSelect.class, id);
		return resource;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserSelect> findAll() {
		String sql = "from UserSelect";
		List<UserSelect> list = this.getHibernateTemplate().find(sql);
		return list;
	}

	@Override
	public List<UserSelect> getAllResourceByPage(int currentPage, int pageSize ,String userName)
			throws Exception {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		String sql = "from UserSelect where selectUser='" + userName + "'";
		List<UserSelect> userDown = showByPage(currentPage, pageSize, session, tx, sql);
		return userDown;
	}

	@Override
	public int getTotal(int pageSize, String userName) throws Exception {
		int totalPage = 0;
		String sql = "from UserSelect where selectUser='" + userName + "'";
		totalPage = getTotalPage(pageSize, sql);
		return totalPage;
	}

	/**
	 * 分页和总页数计算
	 * 基础方法
	 */
	//分页算法
	@SuppressWarnings("unchecked")
	public List<UserSelect> showByPage(int currentPage, int pageSize,
			Session session, Transaction tx, String sql) {
		 Query query=session.createQuery(sql);  
	     //进行分页setFirstResult为起始记录数(currentPage-1)*pageSize算法
		 int startRow=(currentPage-1)*pageSize; 
		 query.setFirstResult(startRow);   
		 //每页显示记录数
		 query.setMaxResults(pageSize);  
		 List<UserSelect> resource=query.list();
		 tx.commit();
		 session.close();
		 return resource;
	}
	//总页数算法
	@SuppressWarnings("unchecked")
	public int getTotalPage(int pageSize, String sql) {
		int totalPage;
		List<UserSelect> list = this.getHibernateTemplate().find(sql);  
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
