package com.dao.shequ;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.Answer;
import com.model.Comment;
import com.model.Question;
import com.model.UserNew;

public class SheQuDaoImpl extends HibernateDaoSupport implements SheQuDao {

	/** ========�������========= **/
	// �ύ����
	public void saveQuestion(Question quest) {
		this.getHibernateTemplate().save(quest);
	}

	public void updateQuestion(Question quest) {
		this.getHibernateTemplate().update(quest);
	}

	public void deleteQuestion(Question quest) {
		this.getHibernateTemplate().delete(quest);
	}

	public Question findByQuestId(int id) {
		Question quest = this.getHibernateTemplate().get(Question.class, id);
		return quest;
	}

	// ��������ҳ��ѯ����
	public List<Question> getQuestionByPage(int currentPage, int pageSize,
			String category, String label, String orderBy, String str,
			String userName) {
		// ��ȡsession
		Session session = this.getSession();
		// ��������
		Transaction tx = session.beginTransaction();
		String sql = null;
		if (str == null && userName == null) {
			if (orderBy == null || orderBy.equals("answerNum")) {
				if (label == null && category == null) {
					sql = "from Question  order by answerNum desc";
				}
				if (label == null && category != null) {
					sql = "from Question v where v.category='" + category
							+ "' order by answerNum desc";
				}
				if (label != null && category.equals("")) {
					sql = "from Question v where v.label='" + label
							+ "' order by answerNum desc";
				}
				if (label != null && category != null) {
					sql = "from Question v where v.label='" + label
							+ "' and v.category='" + category
							+ "' order by answerNum desc";
				}
			} else if (orderBy.equals("questionTime")) {
				if (label == null && category == null) {
					sql = "from Question  order by '" + orderBy + "' desc";
				}
				if (label == null && category != null) {
					sql = "from Question v where v.category='" + category
							+ "' order by '" + orderBy + "' desc";
				}
				if (label != null && category.equals("")) {
					sql = "from Question v where v.label='" + label
							+ "' order by '" + orderBy + "' desc";
				}
				if (label != null && category != null) {
					sql = "from Question v where v.label='" + label
							+ "' and v.category='" + category + "' order by '"
							+ orderBy + "' desc";
				}
			}
		} else {
			sql = "from Question v where v.isSolve='" + str
					+ "' and v.questUser='" + userName + "'";
		}
		List<Question> list = showByPage(currentPage, pageSize, session, tx,
				sql);
		return list;
	}
	public int getQuestionTotal(int pageSize, String category, String label,
			String str, String userName) {
		int totalPage = 0;
		String sql = null;
		if (str == null && userName == null) {
			if (label == null && category == null) {
				sql = "from Question";
			}
			if (label == null && category != null) {
				sql = "from Question v where v.category='" + category + "'";
			}
			if (label != null && category.equals("")) {
				sql = "from Question v where v.label='" + label + "'";
			}
			if (label != null && category != null) {
				sql = "from Question v where v.label='" + label
						+ "' and v.category='" + category + "'";
			}
		} else {
			sql = "from Question v where v.isSolve='" + str
					+ "' and v.questUser='" + userName + "'";
		}

		totalPage = getTotalPage(pageSize, sql);
		return totalPage;
	}

	/** �������� */
	public List<Question> searchQuestionByPage(int currentPage, int pageSize,
			String search) {
		// ��ȡsession
		Session session = this.getSession();
		// ��������
		Transaction tx = session.beginTransaction();
		String sql = "from Question v where  v.category='" + search
				+ "' or v.label='" + search + "' or  v.questionName like'%"
				+ search + "%' order by answerNum desc";
		List<Question> list = showByPage(currentPage, pageSize, session, tx,
				sql);
		return list;
	}
	public int searchQuestionTotal(int pageSize, String search) {
		int totalPage = 0;
		String sql = "from Question v where v.category='" + search
				+ "'or v.label='" + search + "' or v.questionName like '%"
				+ search + "%'";
		totalPage = getTotalPage(pageSize, sql);
		return totalPage;
	}

	/**
	 * ��ҳ����ҳ������ ��������
	 */
	// ��ҳ�㷨
	@SuppressWarnings("unchecked")
	public List<Question> showByPage(int currentPage, int pageSize,
			Session session, Transaction tx, String sql) {
		Query query = session.createQuery(sql);
		// ���з�ҳsetFirstResultΪ��ʼ��¼��(currentPage-1)*pageSize�㷨
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		// ÿҳ��ʾ��¼��
		query.setMaxResults(pageSize);
		List<Question> resource = query.list();
		tx.commit();
		session.close();
		return resource;
	}
	// ��ҳ���㷨
	@SuppressWarnings("unchecked")
	public int getTotalPage(int pageSize, String sql) {
		int totalPage;
		List<Question> list = this.getHibernateTemplate().find(sql);
		// �õ��ܼ�¼��
		int totalSize = list.size();
		// ������ҳ��
		int mod = totalSize % pageSize;
		if (mod == 0) {
			totalPage = totalSize / pageSize;
		} else {
			totalPage = totalSize / pageSize + 1;
		}
		return totalPage;
	}

	/** ========���۲���========= **/
	// �ύ����
	public void saveComment(Comment comment) {
		this.getHibernateTemplate().save(comment);
	}
	public void deleteComment(Comment comment) {
		this.getHibernateTemplate().delete(comment);
	}

	public Comment findByCommentId(int id) {
		Comment quest = this.getHibernateTemplate().get(Comment.class, id);
		return quest;
	}

	/** ========�ش����========= **/
	// �ύ�ش�
	public void saveAnswer(Answer answer) {
		this.getHibernateTemplate().save(answer);
	}
	public void deleteAnswer(Answer comment) {
		this.getHibernateTemplate().delete(comment);
	}
	public Answer findByAnswerId(int id) {
		Answer quest = this.getHibernateTemplate().get(Answer.class, id);
		return quest;
	}

	/** ========��Ϣ����========= **/
	public void deleteNews(UserNew userNew) {
		this.getHibernateTemplate().delete(userNew);
	}

	@SuppressWarnings("unchecked")
	public List<UserNew> findNews(String userName) {
		String sql = "from UserNew un where un.userName='" + userName + "' ";
		List<UserNew> quest = this.getHibernateTemplate().find(sql);
		return quest;
	}

	public UserNew findByNewsId(int id) {
		UserNew quest = this.getHibernateTemplate().get(UserNew.class, id);
		return quest;
	}


}
