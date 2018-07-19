package com.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.IteratorUtils;
import org.apache.struts2.ServletActionContext;

import com.dao.resource.ResourceDao;
import com.dao.shequ.SheQuDao;
import com.dao.user.UserDao;
import com.dao.video.VideoDao;
import com.help.ActionHelp;
import com.help.Help;
import com.help.PageBean;
import com.model.Answer;
import com.model.Comment;
import com.model.Question;
import com.model.Resource;
import com.model.User;
import com.model.UserNew;
import com.model.Video;
import com.opensymphony.xwork2.ActionSupport;

public class SheQuAction extends ActionSupport {
	private static final long serialVersionUID = -1148943485673693881L;

	private Question question;
	private Comment comment;
	private Answer answer;
	private SheQuDao sheQuDao;
	private VideoDao videoDao;
	private ResourceDao resourceDao;
	private UserDao userDao;
	public VideoDao getVideoDao() {
		return videoDao;
	}
	public void setVideoDao(VideoDao videoDao) {
		this.videoDao = videoDao;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public SheQuDao getSheQuDao() {
		return sheQuDao;
	}
	public void setSheQuDao(SheQuDao sheQuDao) {
		this.sheQuDao = sheQuDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public ResourceDao getResourceDao() {
		return resourceDao;
	}
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	private int currentPage=1;//��ǰҳ��
	private int currentPage1 = 1;// ��ǰҳ��
	private int currentPage2 = 1;// ��ǰҳ��
	private int pageSize=5;//ÿҳ��ʾ����
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage1() {
		return currentPage1;
	}
	public void setCurrentPage1(int currentPage1) {
		this.currentPage1 = currentPage1;
	}

	public int getCurrentPage2() {
		return currentPage2;
	}
	public void setCurrentPage2(int currentPage2) {
		this.currentPage2 = currentPage2;
	}
	
	/**========�������=========
	 * @throws Exception **/
	//�ύ����
	public String addQuestion() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();	
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
			if(request.getParameter("quest") != null) {
				Question question = new Question();
				question.setQuestUser(user.getUsername());
				question.setQuestionTime(new Date());
				question.setQuestUser(user.getUsername());
				question.setIntegral(Integer.valueOf(request
						.getParameter("integral")));
				question.setQuestionName(request.getParameter("quest"));
				question.setIsSolve("noSolve");
				sheQuDao.saveQuestion(question);
			}
			request.setAttribute("deall", "3");
			return "addQuestion";
		}else{
			return "BackLogin";
		}
	}

	//ɾ������
	public void deleteQuestion() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Question question = sheQuDao.findByQuestId(Integer.parseInt(request
				.getParameter("questionId")));
		sheQuDao.deleteQuestion(question);
		RequestDispatcher dis = request
				.getRequestDispatcher("jsp/admin/shequ/quest_view.jsp");
		dis.forward(request, response);
	}
		
	//�鿴������ػش�
	@SuppressWarnings("unchecked")
	public String getQuestByAnswer() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileName = request.getParameter("questionId");
		Question question = sheQuDao.findByQuestId(Integer.parseInt(fileName));
		
		Iterator<Answer> itr = question.getAnswer().iterator();
		List<Answer> list = IteratorUtils.toList(itr);
		int begin = (7 * (currentPage - 1));
		int end = 7 * currentPage;
		for (int i = begin; i <= end && i < list.size(); i++) {
			list.get(i);
		}
		new ActionHelp().getShowPage(request,
				new ActionHelp().showPublicByPage(7, list.size()), currentPage,
				list);
		request.setAttribute("question", question);
		return "userQuestByAnswer";
	} 
	@SuppressWarnings("unchecked")
	public String getAdminQuestByAnswer() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileName = request.getParameter("questionId");
		Question question = sheQuDao.findByQuestId(Integer.parseInt(fileName));

		Iterator<Answer> itr = question.getAnswer().iterator();
		List<Answer> list = IteratorUtils.toList(itr);
		int begin = (7 * (currentPage - 1));
		int end = 7 * currentPage;
		for (int i = begin; i <= end && i < list.size() + 1; i++) {
			list.get(i);
		}
		new ActionHelp().getShowPage(request,
				new ActionHelp().showPublicByPage(7, list.size()), currentPage,
				list);
		request.setAttribute("question", question);
		return "adminQuestByAnswer";
	}

	//��������ҳ��ѯ����
	public String ShowQuestionByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String label = null;
		String category = null;
		if (request.getParameter("label") != null) {
			label = new String(request.getParameter("label").getBytes(
					"ISO8859-1"), "UTF-8");
			request.setAttribute("label", label);
		}
		if (request.getParameter("category") != null) {
			category = new String(request.getParameter("category").getBytes(
					"ISO8859-1"), "UTF-8");
			request.setAttribute("category", category);
		}
		String orderBy = request.getParameter("orderby");
		String order = null;
		if (orderBy != null) {
			if (orderBy.equals("answerNum")) {
				order = new Help().getZuire();
			}
			if (orderBy.equals("questionTime")) {
				order = new Help().getZuixin();
			}
		} else {
			order = new Help().getZuixin();
		}
		request.setAttribute("order", order);
		if (currentPage <= 0) {
			currentPage = 1;
		}
		int totalPage = sheQuDao.getQuestionTotal(7, category, label, null,
				null);
		List<Question> resource = sheQuDao.getQuestionByPage(currentPage, 7,
				category, label, orderBy, null, null);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "userListQuest";
	}
	public String ShowAdminQuestByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String label = null;
		String category = null;
		if(request.getParameter("label") != null) {
			label = new String(request.getParameter("label").getBytes("ISO8859-1"),"UTF-8");
			request.setAttribute("label", label);
		}
		if(request.getParameter("category") != null) {
			category = new String(request.getParameter("category").getBytes("ISO8859-1"),"UTF-8");
			request.setAttribute("category", category);
		}
		String orderBy = request.getParameter("orderby");
		String order = null;
		if(orderBy != null){
			if(orderBy.equals("answerNum")){
				order = new Help().getZuire();
			}
			if(orderBy.equals("questionTime")){
				order = new Help().getZuixin();
			}
		}else{
			order = new Help().getZuixin();
		}
		request.setAttribute("order", order);

		if(currentPage <= 0) {
			 currentPage = 1;
		}
		int totalPage = sheQuDao.getQuestionTotal(8, category, label,
				null, null);
		List<Question> resource = sheQuDao.getQuestionByPage(currentPage, 7,
				category, label, orderBy, null, null);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "adminListQuest";
	}
	public void showUserQuest() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String str = request.getParameter("str");
		User user = (User) request.getSession().getAttribute("user");

		if (str.equals("isSolve")) {
			request.setAttribute("Solve", "isSolve");
			request.setAttribute("type", "�ѽ��");
		}
		if (str.equals("noSolve")) {
			request.setAttribute("Solve", "noSolve");
			request.setAttribute("type", "δ���");
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}
		int totalPage = sheQuDao.getQuestionTotal(6, null, null, str,
				user.getUsername());
		List<Question> resource = sheQuDao.getQuestionByPage(currentPage, 6,
				null, null, null, str, user.getUsername());
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		// String url = request.getHeader("referer");
		// System.out.println(url);
		RequestDispatcher dis = request
				.getRequestDispatcher("/jsp/personal/My_Question.jsp");
		dis.forward(request, response);
	}
	// ������ҳ��ѯ
	public String sreachQuestionByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();

		String search = request.getParameter("search");
		if (search != null) {
			request.setAttribute("search", search);
			// �����ǰҳ��С��1����ֵΪ1������ҳ
			if (currentPage <= 0) {
				currentPage = 1;
			}
			Help help = new Help();
			request.setAttribute("help", help);

			/** video���� **/
			int totalPage = videoDao.searchVideoTotal(6, search);
			List<Video> video = videoDao.searchVideoByPage(currentPage, 5,
					search);
			PageBean<Video> pageBean = new PageBean<Video>(currentPage,
					totalPage);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("video", video);

			/** �������� **/
			int totalPage1 = sheQuDao.searchQuestionTotal(10, search);
			List<Question> quest = sheQuDao.searchQuestionByPage(currentPage1,
					8, search);
			PageBean<Question> pageBean1 = new PageBean<Question>(currentPage1,
					totalPage1);
			request.setAttribute("pageBean", pageBean1);
			request.setAttribute("quest", quest);

			/** ��Դ���� **/
			int totalPage2 = resourceDao.searchResTotal(10, search);
			List<Resource> res = resourceDao.searchResByPage(currentPage2, 5,
					search);
			PageBean<Resource> pageBean2 = new PageBean<Resource>(currentPage2,
					totalPage2);
			request.setAttribute("pageBean", pageBean2);
			request.setAttribute("res", res);
			return "showQuestion";
		} else {
			request.setAttribute("search", "��������������");
			return null;
		}
	}

	// ��ҳ
	public String getShouYe() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String isExanube = "is";
		if (currentPage <= 0) {
			currentPage = 1;
		}

		List<Video> video = videoDao.getAllVideoByPage(currentPage, 4, null,
				null, null);
		List<Resource> res = resourceDao.getAllResourceByPage(currentPage, 6,
				null, isExanube, "uploadTime");
		List<Question> quest = sheQuDao.getQuestionByPage(currentPage, 10,
				null, null, "answerNum", null, null);

		request.setAttribute("video", video);
		request.setAttribute("res", res);
		request.setAttribute("quest", quest);
		return "getshouye";
	}

	/**========���۲���=========
	 * @throws IOException 
	 * @throws ServletException **/
	//ɾ������
	public void deleteComment() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();	
		Comment comment = sheQuDao.findByCommentId(Integer.parseInt(request.getParameter("commentId")));
		sheQuDao.deleteComment(comment);
	}
	
	/**========�ش����=========
	 * @throws IOException 
	 * @throws ServletException **/
	//ɾ���ش�
	public void deleteAnswer() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Answer answer = sheQuDao.findByAnswerId(Integer.parseInt(request.getParameter("commentId")));
		sheQuDao.deleteAnswer(answer);
		RequestDispatcher dis = request
				.getRequestDispatcher("/jsp/admin/shequ/answer_list.jsp");
		dis.forward(request, response);
	}
	//�ύ�ش�
	public String addAnswer(){
		HttpServletRequest request = ServletActionContext.getRequest();	
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			Question question = sheQuDao.findByQuestId(Integer.parseInt(request
					.getParameter("questionId")));
			User userQuest = userDao.getUser(question.getQuestUser());
			if(request.getParameter("answer_1") != null){
				Answer answer = new Answer();
				question.setAnswerNum(question.getAnswerNum()+1);
				answer.setAnswerUser(user.getUsername());
				answer.setAnswerTime(new Date());
				answer.setAnswerName(request.getParameter("answer_1"));
				question.getAnswer().add(answer);
				sheQuDao.updateQuestion(question);

				UserNew userNew = new UserNew();
				userNew.setUserName(userQuest.getUsername());
				userNew.setNewsName(user.getUsername() + "��"
						+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())
						+ "�ش����������");
				userNew.setNewsTime(new Date());
				userNew.setNewsRead("δ��");
				userQuest.getUserNew().add(userNew);
				userDao.update(userQuest);
			}
			return "addAnswer";
		}else{
			return "BackLogin";
		}
	}
	
	// ����ش���
	public String AnswerIntegral() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Question quest = sheQuDao.findByQuestId(Integer.valueOf(request
				.getParameter("questId")));
		Answer answer = sheQuDao.findByAnswerId(Integer.parseInt(request
				.getParameter("answerId")));
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("deall", "2");
			return "BackLogin";
		} else {
			User userQuest = userDao.getUser(quest.getQuestUser());
			User userAnswer = userDao.getUser(answer.getAnswerUser());
			if (user.getId().equals(userQuest.getId())) {
				userQuest.setIntegral(userQuest.getIntegral()
						- quest.getIntegral());
				userAnswer.setIntegral(userAnswer.getIntegral()
						+ quest.getIntegral());
				quest.setIsSolve("isSolve");

				UserNew userNew = new UserNew();
				userNew.setUserName(userAnswer.getUsername());
				userNew.setNewsName(user.getUsername() + "����" + "���Ļش�����" + "!"
						+ "���Ļ�������" + quest.getIntegral());
				userNew.setNewsTime(new Date());
				userNew.setNewsRead("δ��");

				userAnswer.getUserNew().add(userNew);
				userDao.update(userQuest);
				userDao.update(userAnswer);
				sheQuDao.updateQuestion(quest);
				return null;
			} else {
				request.setAttribute("deall", "1");
				return "NotQuestUser";
			}
		}
	}

	/**
	 * ======��Ϣ����=======
	 **/
	// ��Ϣ�鿴
	@SuppressWarnings("unchecked")
	public void getuserNews() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			Iterator<UserNew> itr = user.getUserNew().iterator();
			List<UserNew> list = IteratorUtils.toList(itr);
			if (list.size() > 0) {
				String mesage = "����" + list.size() + "����Ϣδ��";
				request.setAttribute("mesage", mesage);
			} else {
				String mesage = "û��δ����Ϣ";
				request.setAttribute("mesage", mesage);
			}
			int begin = (10 * (currentPage - 1));
			int end = 10 * currentPage;
			for (int i = begin; i <= end && i < list.size(); i++) {
				list.get(i);
			}
			new ActionHelp().getShowPage(request,
					new ActionHelp().showPublicByPage(10, list.size()),
					currentPage, list);
			int totalSize = new ActionHelp().showPublicByPage(10, list.size());
			new ActionHelp().getShowPage(request, totalSize, currentPage, list);
			RequestDispatcher dis = request
					.getRequestDispatcher("/jsp/personal/My_News.jsp");
			dis.forward(request, response);
		}
	}

	// ��Ϣɾ��
	public void deleteUserNews() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		UserNew userNew = sheQuDao.findByNewsId(Integer.parseInt(request
				.getParameter("newsId")));
		sheQuDao.deleteNews(userNew);
		RequestDispatcher dis = request
				.getRequestDispatcher("/jsp/personal/My_News.jsp");
		dis.forward(request, response);
	}
}
