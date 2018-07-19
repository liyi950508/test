package com.dao.shequ;

import java.util.List;

import com.model.Answer;
import com.model.Comment;
import com.model.Question;
import com.model.UserNew;

public interface SheQuDao {

	/** ========问题操作========= **/
	void saveQuestion(Question quest); // 提交问题
	void updateQuestion(Question quest);
	void deleteQuestion(Question quest);
	Question findByQuestId(int id);
	// 按条件分页查询问题
	List<Question> getQuestionByPage(int currentPage, int pageSize,
			String label, String category, String orderBy, String str,
			String userName);
	// 获取符合条件的问题的总记录数
	int getQuestionTotal(int pageSize, String label, String category,
			String str, String userName);
	// 搜索查询问题
	public List<Question> searchQuestionByPage(int currentPage, int pageSize,
			String search);
	public int searchQuestionTotal(int pageSize, String search);

	/** ========评论操作========= **/
	void saveComment(Comment comment); // 提交评论
	void deleteComment(Comment commeng);

	Comment findByCommentId(int Id);

	/** ========回答操作========= **/
	void saveAnswer(Answer answer); // 提交回答
	void deleteAnswer(Answer answer);
	Answer findByAnswerId(int Id);

	/** ========消息操作========= **/
	void deleteNews(UserNew userNew);

	UserNew findByNewsId(int id);

	List<UserNew> findNews(String userName);
}
