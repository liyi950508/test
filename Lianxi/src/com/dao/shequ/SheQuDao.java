package com.dao.shequ;

import java.util.List;

import com.model.Answer;
import com.model.Comment;
import com.model.Question;
import com.model.UserNew;

public interface SheQuDao {

	/** ========�������========= **/
	void saveQuestion(Question quest); // �ύ����
	void updateQuestion(Question quest);
	void deleteQuestion(Question quest);
	Question findByQuestId(int id);
	// ��������ҳ��ѯ����
	List<Question> getQuestionByPage(int currentPage, int pageSize,
			String label, String category, String orderBy, String str,
			String userName);
	// ��ȡ����������������ܼ�¼��
	int getQuestionTotal(int pageSize, String label, String category,
			String str, String userName);
	// ������ѯ����
	public List<Question> searchQuestionByPage(int currentPage, int pageSize,
			String search);
	public int searchQuestionTotal(int pageSize, String search);

	/** ========���۲���========= **/
	void saveComment(Comment comment); // �ύ����
	void deleteComment(Comment commeng);

	Comment findByCommentId(int Id);

	/** ========�ش����========= **/
	void saveAnswer(Answer answer); // �ύ�ش�
	void deleteAnswer(Answer answer);
	Answer findByAnswerId(int Id);

	/** ========��Ϣ����========= **/
	void deleteNews(UserNew userNew);

	UserNew findByNewsId(int id);

	List<UserNew> findNews(String userName);
}
