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
	//保存资源
	public void save(Resource resource) {
		this.getHibernateTemplate().save(resource);
	}
	//按名称查找资源
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
	//更新资源
	public void update(Resource resourceName) {
		this.getHibernateTemplate().update(resourceName);
	}

	//查找所有资源
	@SuppressWarnings("unchecked")
	public List<Resource> findAll() {
		String hsql = "from Resource";
		List<Resource> list = this.getHibernateTemplate().find(hsql);
		return list;
	}
	//删除资源
	public void delete(Resource resource) {
		this.getHibernateTemplate().delete(resource);
	}

	//按id号查找资源
	public Resource findById(int id) {
		Resource resource = this.getHibernateTemplate().get(Resource.class, id);
		return resource;
	}

	//分页查询资源方法
	public List<Resource> getAllResourceByPage(int currentPage, int pageSize,
			String label, String isExamine, String orderBy)
			throws Exception {
		  //获取session
		  Session session=this.getSession();
		  //开启事务
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
	//	按字段分页查询资源
	public List<Resource> getResourceByPage(int currentPage, int pageSize,
			String resourceName, String uploadUser, String label,
			String category,String type, String orderBy) throws Exception {
		 //获取session
		 Session session=this.getSession();
		 //开启事务
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
	 * 查询总记录数并计算总页数
	 */
	//按字段查询总记录数并计算总页数
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
	//查询总记录数并计算总页数
	public int getAllTotal(int pageSize, String label, String isExamine)
			throws Exception {
          //初始化总页数
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
	 * 分页和总页数计算
	 * 基础方法
	 */
	//分页算法
	@SuppressWarnings("unchecked")
	public List<Resource> showByPage(int currentPage, int pageSize,
			Session session, Transaction tx, String sql) {
		 Query query=session.createQuery(sql);  
	     //进行分页setFirstResult为起始记录(currentPage-1)*pageSize算法
		 int startRow=(currentPage-1)*pageSize; 
		 query.setFirstResult(startRow);   
		 //每页最后显示记录
		 query.setMaxResults(pageSize);  
		 List<Resource> resource=query.list();
		 tx.commit();
		 session.close();
		 return resource;
	}
	//总页数算法
	@SuppressWarnings("unchecked")
	public int getTotalPage(int pageSize, String sql) {
		int totalPage;
		List<Resource> list = this.getHibernateTemplate().find(sql);  
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

	/**
	 * 搜索查询资源
	 */
	public List<Resource> searchResByPage(int currentPage, int pageSize,
			String search) {
		// 获取session
		Session session = this.getSession();
		// 开启事务
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
