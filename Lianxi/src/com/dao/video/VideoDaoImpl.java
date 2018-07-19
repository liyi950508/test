package com.dao.video;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.UserVideo;
import com.model.Video;

public class VideoDaoImpl extends HibernateDaoSupport implements VideoDao {
	
	@Override
	public void add(Video video) {
		this.getHibernateTemplate().save(video);
	}

	@Override
	public void delete(Video video) {
		this.getHibernateTemplate().delete(video);
	}

	@Override
	public void update(Video video) {
		this.getHibernateTemplate().update(video);
	}

	//�����Ʋ�����Ƶ
	@SuppressWarnings({"unchecked" })
	public Video findByName(String videoName, String videoLabel, String videoAuthor, String category) {
		String hsql = "from Video v where v.videoName='" +videoName+ "'";
		List<Video> list = (List<Video>)this.getHibernateTemplate().find(hsql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	////�����߲�����Ƶ
	@SuppressWarnings("unchecked")
	public Video findByAuthor(String videoAuthor){
		String hsql = "from Video v where v.videoAuthor='" +videoAuthor+ "'";
		List<Video> list = (List<Video>)this.getHibernateTemplate().find(hsql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings({"unchecked" })
	public List<Video> findAll() {
		String sql = "form Video v order by studyNum desc";
		List<Video> list = this.getHibernateTemplate().find(sql);
		return list;
	}

	//��id�Ų�����Ƶ
	public Video findById(int id) {
		Video video = this.getHibernateTemplate().get(Video.class, id);
		return video;
	}

	/**
	 * ���û�������Դ����ѯ�û���Ƶ��
	 */
	@SuppressWarnings("unchecked")
	public UserVideo getUserVideo(String user, String videoName) {
		String sql = "from UserVideo where videoUser='"+user+"' and videoName='" +videoName+ "'";
		List<UserVideo> list = (List<UserVideo>)this.getHibernateTemplate().find(sql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	
	//��ҳ��ѯȫ����Ƶ����
	public List<Video> getAllVideoByPage(int currentPage, int pageSize,String category, String label, String orderBy) {
		//��ȡsession
		Session session=this.getSession();
		//��������
		Transaction tx=session.beginTransaction();
		String sql = null;
		if (orderBy == null || orderBy.equals("updateTime")) {
			if(label==null && category==null){
				sql = "from Video order by updateTime desc";
			}
			if(label==null && category!=null){
				sql="from Video v where v.category='" +category+ "' order by updateTime desc";
			}
			if(label!=null && category.equals("")){
				sql="from Video v where v.videoLabel='" +label+ "' order by updateTime desc";	
			}
			if(label!=null && category!=null){
				sql="from Video v where v.videoLabel='" +label+ "' and v.category='" +category+ "' order by updateTime desc";
			}
		} else {
			if(label==null && category==null){
				sql = "from Video order by studyNum desc";
			}
			if(label==null && category!=null){
				sql = "from Video v where v.category='" + category
						+ "' order by studyNum desc";
			}
			if(label!=null && category.equals("")){
				sql = "from Video v where v.videoLabel='" + label
						+ "' order by studyNum desc";
			}
			if(label!=null && category!=null){
				sql = "from Video v where v.videoLabel='" + label
						+ "' and v.category='" + category
						+ "' order by studyNum desc";
			}
		}
		List<Video> list = showByPage(currentPage, pageSize, session, tx, sql);
		return list;
	}
	//��ѯ�ܼ�¼����������ҳ��
	public int getVideoTotal(int pageSize, String category, String label) throws Exception {
		int totalPage = 0;
		String sql = null;
		if ((label == null) && (category == null)) {
			sql = "from Video";
		}
		if ((label == null) && category != null) {
			sql="from Video v where v.category='" +category+ "'";
		}
		if (label != null && category == null) {
			sql="from Video v where v.videoLabel='" +label+ "'";	
		}
		if (label != null && category != null) {
			sql="from Video v where v.videoLabel='" +label+ "' and v.category='" +category+ "'";
		}
		totalPage = getTotalPage(pageSize, sql);
		return totalPage;
	}
	
	/**
	 * ������ѯ
	 */
	public List<Video> searchVideoByPage(int currentPage, int pageSize,
			String searech) {
		// ��ȡsession
		Session session = this.getSession();
		// ��������
		Transaction tx = session.beginTransaction();
		String sql = "from Video v where  v.videoName like'%" + searech
				+ "%' or v.videoLabel='" + searech + "' or  v.category='"
				+ searech + "'";
		// String sql = "from Video as r where 1=1 ";
		// if (searech != null) {
		// sql += "and r.videoName like '%" + searech + "%'";
		// }
		// if (searech != null) {
		// sql += " or r.category='" + searech + "'";
		// }
		// if (searech != null) {
		// sql += " or r.videoLabel='" + searech + "'";
		// }
		List<Video> list = showByPage(currentPage, pageSize, session, tx, sql);
		return list;
	}

	public int searchVideoTotal(int pageSize, String searech) {
		int totalPage = 0;
		String sql = "from Video v where v.videoName like'%" + searech + "%'"
				+ "' or v.videoLabel='" + searech + "' or v.category='"
				+ searech + "'";
		// String sql = "from Video as r where 1=1 ";
		// if (searech != null) {
		// sql += " and r.videoName like '%" + searech + "%'";
		// }
		// if (searech != null) {
		// sql += " or r.category='" + searech + "'";
		// }
		// if (searech != null) {
		// sql += " or r.videoLabel='" + searech + "'";
		// }
		totalPage = getTotalPage(pageSize, sql);
		return totalPage;
	}

	/**
	 * ��ҳ����ҳ������ ��������
	 */
	//��ҳ�㷨
	@SuppressWarnings("unchecked")
	public List<Video> showByPage(int currentPage, int pageSize,
			Session session, Transaction tx, String sql) {
		 Query query=session.createQuery(sql);  
	     //���з�ҳsetFirstResultΪ��ʼ��¼��(currentPage-1)*pageSize�㷨
		 int startRow=(currentPage-1)*pageSize; 
		 query.setFirstResult(startRow);   
		 //ÿҳ��ʾ��¼��
		 query.setMaxResults(pageSize);  
		 List<Video> resource=query.list();
		 tx.commit();
		 session.close();
		 return resource;
	}
	//��ҳ���㷨
	@SuppressWarnings("unchecked")
	public int getTotalPage(int pageSize, String sql) {
		int totalPage;
		List<Video> list = this.getHibernateTemplate().find(sql);  
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
