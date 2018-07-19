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
	 * ��ҳ��ѯ
	 * չʾ��Դ
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
	 * ��ҳ����ҳ������
	 * ��������
	 */
	//��ҳ�㷨
	@SuppressWarnings("unchecked")
	public List<UserDownload> showByPage(int currentPage, int pageSize,
			Session session, Transaction tx, String sql) {
		 Query query=session.createQuery(sql);  
	     //���з�ҳsetFirstResultΪ��ʼ��¼��(currentPage-1)*pageSize�㷨
		 int startRow=(currentPage-1)*pageSize; 
		 query.setFirstResult(startRow);   
		 //ÿҳ��ʾ��¼��
		 query.setMaxResults(pageSize);  
		 List<UserDownload> resource=query.list();
		 tx.commit();
		 session.close();
		 return resource;
	}
	//��ҳ���㷨
	@SuppressWarnings("unchecked")
	public int getTotalPage(int pageSize, String sql) {
		int totalPage;
		List<UserDownload> list = this.getHibernateTemplate().find(sql);  
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
