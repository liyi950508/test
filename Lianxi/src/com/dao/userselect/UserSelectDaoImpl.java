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

	//�����Ʋ�ѯ��Դ��
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
	 * ��ҳ����ҳ������
	 * ��������
	 */
	//��ҳ�㷨
	@SuppressWarnings("unchecked")
	public List<UserSelect> showByPage(int currentPage, int pageSize,
			Session session, Transaction tx, String sql) {
		 Query query=session.createQuery(sql);  
	     //���з�ҳsetFirstResultΪ��ʼ��¼��(currentPage-1)*pageSize�㷨
		 int startRow=(currentPage-1)*pageSize; 
		 query.setFirstResult(startRow);   
		 //ÿҳ��ʾ��¼��
		 query.setMaxResults(pageSize);  
		 List<UserSelect> resource=query.list();
		 tx.commit();
		 session.close();
		 return resource;
	}
	//��ҳ���㷨
	@SuppressWarnings("unchecked")
	public int getTotalPage(int pageSize, String sql) {
		int totalPage;
		List<UserSelect> list = this.getHibernateTemplate().find(sql);  
		  //�õ��ܼ�¼��
		  int totalSize = list.size();
		  //������ҳ��
		  int mod=totalSize%pageSize;
		  if(mod==0){
			  totalPage=totalSize/pageSize;
		  }else{
			  totalPage=totalSize/pageSize+1;
		  }
		return totalPage;
	}
	
}
