package com.dao.resource;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.help.Help;
import com.model.Resource;

public class ResourceDaoImpl extends HibernateDaoSupport implements ResourceDao {
	String isExamine = "is";
	String sql = "from Resource v where v.isExamine='" + isExamine + "'";
	//������Դ
	public void save(Resource resource) {
		this.getHibernateTemplate().save(resource);
	}
	//�����Ʋ�����Դ
	@SuppressWarnings("unchecked")
	public Resource getResource(String resourceName, String uploadUser, 
			String label, String category) {
		String hsql = "from Resource r where r.resourceName='" +resourceName+ "'";
		List<Resource> list = (List<Resource>)this.getHibernateTemplate().find(hsql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	//������Դ
	public void update(Resource resourceName) {
		this.getHibernateTemplate().update(resourceName);
	}

	//����������Դ
	@SuppressWarnings("unchecked")
	public List<Resource> findAll() {
		String hsql = "from Resource";
		List<Resource> list = this.getHibernateTemplate().find(hsql);
		return list;
	}
	//ɾ����Դ
	public void delete(Resource resource) {
		this.getHibernateTemplate().delete(resource);
	}

	//��id�Ų�����Դ
	public Resource findById(int id) {
		Resource resource = this.getHibernateTemplate().get(Resource.class, id);
		return resource;
	}

	//��ҳ��ѯ��Դ����
	public List<Resource> getAllResourceByPage(int currentPage, int pageSize,
			String label, String isExamine, String orderBy)
			throws Exception {
		  //��ȡsession
		  Session session=this.getSession();
		  //��������
		  Transaction tx=session.beginTransaction();
		String sql = "from Resource v where v.isExamine='" + isExamine + "'";
		  if(orderBy == null || orderBy.equals("uploadTime")){
			if (label == null) {
				sql = sql + " " + "order by uploadTime desc";
			  }
			if (label != null) {
				sql = sql + " " + "and v.label='" + label
						+ "' order by uploadTime desc";
			  }
		} else {
			if (label != null) {
				sql = sql + " " + "order by lownloadNumber desc";
				 }
			if (label != null) {
				sql = sql + " " + "and v.label='" + label
						+ "' order by downloadNumber desc";
				 }
		  }

		List<Resource> resource = showByPage(currentPage, pageSize, session,
				tx, sql);
		  return resource; 
	}
	//	���ֶη�ҳ��ѯ��Դ
	public List<Resource> getResourceByPage(int currentPage, int pageSize,
			String resourceName, String uploadUser, String label,
			String category,String type, String orderBy) throws Exception {
		 //��ȡsession
		 Session session=this.getSession();
		 //��������
		 Transaction tx=session.beginTransaction();
		String sql1 = null;
	     if(uploadUser != null) {
			sql1 = sql + " " + "and v.uploadUser='" + uploadUser + "'";
	     } else{
	    	 if(type.equals(new Help().getJQXX())) {
				if (label == null && category == null) {
					sql1 = sql + " " + "and v.type='" + type + "'";
				 }
				if ((label == null) && category != null) {
					sql1 = sql + " " + "and v.type='" + type
							+ "' and v.category='" + category + "'";
				 }
				if (label != null && category == null) {
					sql1 = sql + " " + "and v.type='" + type
							+ "' and v.label='"
							+ label + "'";
				 }
				if (label != null && category != null) {
					sql1 = sql + " " + "and v.type='" + type
							+ "' and v.label='"
							+ label + "' and v.category='" + category + "'";
				 }
			 }
			 if(type.equals(new Help().getMSSB())) {
				if (label == null && category == null) {
					sql1 = sql + " " + "and v.type='" + type + "'";
				 }
				if ((label == null) && category != null) {
					sql1 = sql + " " + "and v.type='" + type
							+ "' and v.category='" + category + "'";
				 }
				if (label != null && category == null) {
					sql1 = sql + " " + "and v.type='" + type
							+ "' and v.label='"
							+ label + "'";
				 }
				if (label != null && category != null) {
					sql1 = sql + " " + "and v.type='" + type
							+ "' and v.label='"
							+ label + "' and v.category='" + category + "'";
				 }
			 }
	     }
		List<Resource> resource = showByPage(currentPage, pageSize, session,
				tx, sql1);
		 return resource; 
	}
	
	/**
	 * ��ѯ�ܼ�¼����������ҳ��
	 */
	//���ֶβ�ѯ�ܼ�¼����������ҳ��
	public int getTotal(int pageSize, String resourceName, String uploadUser,
			String label, String category, String type) throws Exception {
	     int totalPage = 0;
		String sql1 = null;
	     if(uploadUser != null) {
			sql1 = sql + " " + "and v.uploadUser='" + uploadUser + "'";
	     } else{
	    	  if(type.equals(new Help().getJQXX())) {
				if (label == null && category == null) {
					sql1 = sql + " " + "and v.type='" + type + "'";
				 }
				if ((label == null) && category != null) {
					sql1 = sql + " " + "and v.type='" + type
							+ "' and v.category='" + category + "'";
				 }
				if (label != null && category == null) {
					sql1 = sql + " " + "and v.type='" + type
							+ "' and v.label='" + label + "'";
				 }
				if (label != null && category != null) {
					sql1 = sql + " " + "and v.type='" + type
							+ "' and v.label='" + label + "' and v.category='"
							+ category + "'";
				 }
			 }
			 if(type.equals(new Help().getMSSB())) {
				if (label == null && category == null) {
					sql1 = sql + " " + "and v.type='" + type + "'";
				 }
				if ((label == null) && category != null) {
					sql1 = sql + " " + "and v.type='" + type
							+ "' and v.category='" + category + "'";
				 }
				if (label != null && category == null) {
					sql1 = sql + " " + "and v.type='" + type
							+ "' and v.label='" + label + "'";
				 }
				if (label != null && category != null) {
					sql1 = sql + " " + "and v.type='" + type
							+ "' and v.label='" + label + "' and v.category='"
							+ category + "'";
				 }
			 }
	     }
		
		totalPage = getTotalPage(pageSize, sql1);
		 return totalPage;
	}
	//��ѯ�ܼ�¼����������ҳ��
	public int getAllTotal(int pageSize, String label, String isExamine)
			throws Exception {
          //��ʼ����ҳ��
	      int totalPage = 0;
		String sql = "from Resource v where v.isExamine='" + isExamine + "'";
		String sql1 = null;
		  if(label == null) {
			sql1 = sql;
		  }
		  if(label != null){
			sql1 = sql + " " + "and v.label='" + label + "'";
		  }
		totalPage = getTotalPage(pageSize, sql1);
		  return totalPage;
	}
	
	/**
	 * ��ҳ����ҳ������
	 * ��������
	 */
	//��ҳ�㷨
	@SuppressWarnings("unchecked")
	public List<Resource> showByPage(int currentPage, int pageSize,
			Session session, Transaction tx, String sql) {
		 Query query=session.createQuery(sql);  
	     //���з�ҳsetFirstResultΪ��ʼ��¼(currentPage-1)*pageSize�㷨
		 int startRow=(currentPage-1)*pageSize; 
		 query.setFirstResult(startRow);   
		 //ÿҳ�����ʾ��¼
		 query.setMaxResults(pageSize);  
		 List<Resource> resource=query.list();
		 tx.commit();
		 session.close();
		 return resource;
	}
	//��ҳ���㷨
	@SuppressWarnings("unchecked")
	public int getTotalPage(int pageSize, String sql) {
		int totalPage;
		List<Resource> list = this.getHibernateTemplate().find(sql);  
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

	/**
	 * ������ѯ��Դ
	 */
	public List<Resource> searchResByPage(int currentPage, int pageSize,
			String search) {
		// ��ȡsession
		Session session = this.getSession();
		// ��������
		Transaction tx = session.beginTransaction();
		String sql1 = sql + " " + "and v.resourceName like'%" + search
				+ "%' or v.label='" + search + "' or  v.category='" + search
				+ "' or v.type='" + search + "' order by v.downloadNumber desc";
		List<Resource> list = showByPage(currentPage, pageSize, session, tx,
				sql1);
		return list;
	}
	public int searchResTotal(int pageSize, String search) {
		int totalPage = 0;
		String sql1 = sql + " " + "and v.resourceName like'%" + search
				+ "%' or v.label='" + search + "' or v.category='" + search
				+ "' or v.type='" + search + "'";
		totalPage = getTotalPage(pageSize, sql1);
		return totalPage;
	}


}
