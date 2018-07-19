package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
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
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.user.UserDao;
import com.dao.uservideo.UserVideoDao;
import com.dao.video.VideoDao;
import com.help.ActionHelp;
import com.help.CurrentTimeHelp;
import com.help.Help;
import com.model.Question;
import com.model.User;
import com.model.UserVideo;
import com.model.Video;
import com.opensymphony.xwork2.ActionSupport;

public class VideoAction extends ActionSupport {
	private static final long serialVersionUID = 1018906045559564389L;
	HibernateDaoSupport hi;
	Date date = new Date();
	
	private Video video;
	private VideoDao videoDao;
	private UserVideoDao userVideoDao;
	private UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public Video getVideo() { 
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public VideoDao getVideoDao() {
		return videoDao;
	}
	public void setVideoDao(VideoDao videoDao) {
		this.videoDao = videoDao;
	}

	public UserVideoDao getUserVideoDao() {
		return userVideoDao;
	}

	public void setUserVideoDao(UserVideoDao userVideoDao) {
		this.userVideoDao = userVideoDao;
	}
	
	private File uploadFile;		 	 // 用户上传的文件
    private String uploadFileFileName;  // 上传文件的文件名
    private String uploadFileContentType;// 上传文件的类型
    private InputStream inputStream;     //资源传输流
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUploadFileFileName() {
		return uploadFileFileName;
	}
	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
	public String getUploadFileContentType() {
		return uploadFileContentType;
	}
	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	private String contentDisposition;
	public String getContentDisposition() {
		return contentDisposition;
	}
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	
	private int currentPage=1;//当前页码
	private int pageSize=5;//每页显示条数
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
	
	//视频上传
	public String addVideo() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		//得到保存目录
		String Path = "upload/video/";
		//根据文件名uploadFileFileName数据库查询
		Video videoName = videoDao.findByName(uploadFileFileName, null, null, null);
		//获取上传文件的格式
		String extention = uploadFileFileName.substring(uploadFileFileName.lastIndexOf(".")+1);
	    
		//限制文件上传格式
		if(extention.equals("mp4")){
			if(uploadFile != null && videoName == null) {
				//给文件赋新名称
				String datepath = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
				String filename = datepath+"."+extention;
				//文件保存路径
				String Path1 = ServletActionContext.getServletContext().getRealPath(Path);
				String savePath = Path1+"\\" + filename;
				//数据库存储路径
				String savePath1 = Path + filename;
				//保存视频
				File saveVideo = new File(savePath);
				uploadFile.renameTo(saveVideo);
				
				String videoAuthor = request.getParameter("videoAuthor");
				String intro = request.getParameter("intro");
				String label = request.getParameter("videoLabel");
				String category = request.getParameter("category");
						
				Video video = new Video();
				//给video对象赋值
				video.setVideoName(uploadFileFileName);
				video.setVideoType(uploadFileContentType);
				video.setUpdateTime(date);
				video.setStudyNum(video.getStudyNum());
				video.setCategory(category);
				video.setCourseIntro(intro);
				video.setVideoLabel(label);
				video.setStudyNum(0);
				video.setVideoAuthor(videoAuthor);
				video.setVideoPath(savePath1);
				videoDao.add(video);
				request.setAttribute("deall", "4");
				return "videoUpSuccess";
			} else {
				request.setAttribute("deall", "5");
				return "fileNameError";
			}

		} else {
			request.setAttribute("deall", "6");
			return "extentionError";
		}
	}
	
	//下载视频
	public String downloadVideo() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String path = ServletActionContext.getServletContext().getRealPath("");
		User downuser = (User) request.getSession().getAttribute("user");
		if(downuser == null) {
			return "backLogin";
		}else{
			String videoId = request.getParameter("videoId");
			Video video = videoDao.findById(Integer.parseInt(videoId));
			String videoName = video.getVideoName();
			String videoPath = video.getVideoPath();
			String downVideoName = new String(videoName.getBytes("ISO8859-1"),"UTF-8");

			if (downuser.getIntegral() >= 100) {
				/** 文件下载 **/
				String downPath = path + "\\" + videoPath;
				File file = new File(downPath);
				inputStream = new FileInputStream(file);
				// 设置缓冲区大小
				response.setBufferSize(2048);
				// 下载文件类型定义
				response.setContentType("application/octet-stream");
				// 设置下载文件处理方式
				setContentDisposition("attachment;filename="
						+ URLEncoder.encode(downVideoName, "UTF-8"));
				// 下载文件输出流定义
				setInputStream(inputStream);

				downuser.setIntegral(downuser.getIntegral() - 100);
				userDao.update(downuser);

				// **将下载的视频存储到用户视频表**
				UserVideo userVideo = videoDao.getUserVideo(
						downuser.getUsername(), downVideoName);
				UserVideo userDown = new UserVideo();
				if (userVideo == null) {
					userDown.setVideoName(video.getVideoName());
					userDown.setVideoType(video.getVideoType());
					userDown.setVideoUser(downuser.getUsername());
					userDown.setVideoAuthor(video.getVideoAuthor());
					userDown.setLabel(video.getVideoLabel());
					userDown.setDownload(new Help().getDownload());
					userDown.setCategory(video.getCategory());
					userDown.setVideoPath(video.getVideoPath());
					video.setUserVideo(userDown);
					videoDao.update(video);
				} else {
					String userVideoLabel = userVideo.getDownload();
					if (userVideoLabel == null) {
						userVideo.setDownload(new Help().getDownload());
						video.setUserVideo(userVideo);
						videoDao.update(video);
					}
				}
				return "downVideoSuccess";
			} else {
				request.setAttribute("deall", "11");
				return "NotIntegral";
			}
		}	
	}
	
	/**
	 * 收藏视频
	 * @return
	 * @throws Exception
	 */
	public String selectVideo() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
    	User user = (User) request.getSession().getAttribute("user");
    	if(user == null) {
    		return "BackLogin";
    	} else{
	    	Video video = videoDao.findById(Integer.parseInt(request.getParameter("videoId")));
	    	UserVideo userVideo = videoDao.getUserVideo(user.getUsername(), video.getVideoName());
	    	UserVideo userselect = new UserVideo();
	    	if(userVideo == null) {
	    		userselect.setVideoName(video.getVideoName());
				userselect.setVideoType(video.getVideoType());
				userselect.setVideoUser(user.getUsername());
				userselect.setVideoAuthor(video.getVideoAuthor());
				userselect.setLabel(video.getVideoLabel());
				userselect.setCollent(new Help().getCollent());
				userselect.setCategory(video.getCategory());
				userselect.setVideoPath(video.getVideoPath());
				userVideoDao.saveUserVideo(userselect);
				return null;
	    	}else{
	    		String userselectName = userVideo.getCollent();
				if(userselectName == null){
					userVideo.setCollent(new Help().getCollent());
					video.setUserVideo(userVideo);
					userVideoDao.update(userVideo);
					return null;
				} else {
					request.setAttribute("deall", "14");
					return "selectVideoError";
				}
	    	}
    	}  	
	}
	
	//获取视频并返回传参给页面
	public void getVideoByName() throws ServletException, IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			RequestDispatcher dispather = request.getRequestDispatcher("/jsp/userInfo/UserLogin.jsp");
			dispather.forward(request, response);
		}else{
			Video video = videoDao.findByName(
					new String(request.getParameter("videoName").getBytes(
							"ISO8859-1"), "UTF-8"), null, null, null);
			Iterator<Question> itr = video.getQuestion().iterator();
			if(itr.hasNext()){
				Question quest = itr.next();
				request.setAttribute("question", quest);
			}
			request.setAttribute("video", video);
			RequestDispatcher dispather = request.getRequestDispatcher("/jsp/video/video_watch.jsp");
			dispather.forward(request, response);
		}
	}
	
	//查看视频相关问题
	@SuppressWarnings("unchecked")
	public String getVideoByQuest() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileName = request.getParameter("videoId");
		Video video = videoDao.findById(Integer.parseInt(fileName));
		
		Iterator<Question> itr = video.getQuestion().iterator();
		List<Question> list = IteratorUtils.toList(itr);
		int begin = (7 * (currentPage - 1));
		int end = 7 * currentPage;
		for (int i = begin; i <= end && i < list.size(); i++) {
			list.get(i);
		}
		int totalSize = new ActionHelp().showPublicByPage(7, list.size());
		new ActionHelp().getShowPage(request, totalSize, currentPage, list);

		request.setAttribute("video", video);
		return "userListVideo";
	}
	@SuppressWarnings("unchecked")
	public String getAdminVideoByQuest() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileName = request.getParameter("videoId");
		Video video = videoDao.findById(Integer.parseInt(fileName));
		
		Iterator<Question> itr = video.getQuestion().iterator();
		List<Question> list = IteratorUtils.toList(itr);
		int begin = (7 * (currentPage - 1));
		int end = 7 * currentPage;
		for (int i = begin; i <= end && i < list.size(); i++) {
			list.get(i);
		}
		int totalSize = new ActionHelp().showPublicByPage(5, list.size());
		new ActionHelp().getShowPage(request, totalSize, currentPage, list);
		
		request.setAttribute("video", video);
		return "adminListVideo";
	}
	
	//获取视频已播放时间和总时间
 	public void getVideCurrentTime() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		Video video = videoDao.findById(Integer.parseInt(request
				.getParameter("videoId")));
		UserVideo userVideo1 = videoDao.getUserVideo(user.getUsername(),
				video.getVideoName());
		String currentTime = request.getParameter("playtime");
		String coursealltime = request.getParameter("currentTime");

		// 视频video操作
		String courseAllTime = video.getCourseAlltime();
		if(courseAllTime == null) {
			video.setCourseAlltime(coursealltime);
		} 

		// 用户视频UserVideo操作
		UserVideo uservideo = new UserVideo();
		if(userVideo1 == null) {
			uservideo.setVideoName(video.getVideoName());
			uservideo.setVideoAuthor(video.getVideoAuthor());
			uservideo.setVideoType(video.getVideoType());
			uservideo.setVideoUser(user.getUsername());
			uservideo.setLabel(video.getVideoLabel());
			uservideo.setCategory(video.getCategory());
			uservideo.setVideoPath(video.getVideoPath());
			uservideo.setCurrentTime(new CurrentTimeHelp()
					.getcurrentTime(currentTime));
			uservideo.setAllTime(coursealltime);
			if (!Double.valueOf(coursealltime).equals(
					Double.valueOf(currentTime))) {
				uservideo.setStudy(new Help().getStudy_0());
			}
			if (Double.valueOf(coursealltime).equals(
					Double.valueOf(currentTime))) {
				uservideo.setStudy(new Help().getStudy_1());
			}
			// 视频video操作
			if ((Double.valueOf(currentTime) > 10)) {
				video.setStudyNum(video.getStudyNum()+1);
			}
			video.setUserVideo(uservideo);
		}else{
			if (!Double.valueOf(coursealltime).equals(
					Double.valueOf(currentTime))) {
				userVideo1.setStudy(new Help().getStudy_0());
			}
			if (Double.valueOf(coursealltime).equals(
					Double.valueOf(currentTime))) {
				userVideo1.setStudy(new Help().getStudy_1());
			}
			// 视频video操作
			if ((Double.valueOf(currentTime) > 10) && (userVideo1.getStudy() == null)) {
				video.setStudyNum(video.getStudyNum()+1);
			}
			userVideo1.setCurrentTime(new CurrentTimeHelp()
					.getcurrentTime(currentTime));
			userVideo1.setAllTime(courseAllTime);
			video.setUserVideo(userVideo1);
		}
		videoDao.update(video);
	}
	
	//删除视频
	public String AdminDeleteVideo() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String videoId = request.getParameter("videoId");
		Video video = videoDao.findById(Integer.parseInt(videoId));
		String path = ServletActionContext.getServletContext().getRealPath("");
		String videoPath = path+"\\"+video.getVideoPath();
		File file = new File(videoPath);
		file.delete();
		videoDao.delete(video);
		return "DeleteVideoSucces";
	}
	
	//提交问题
	public String addQuestion(){
		HttpServletRequest request = ServletActionContext.getRequest();	
		User user = (User) request.getSession().getAttribute("user");
		Video video = videoDao.findById(Integer.parseInt(request.getParameter("videoId")));
		if(user.getUsername() != null) {
			if(request.getParameter("quest") != null){
				Question question = new Question();
				question.setQuestUser(user.getUsername());
				question.setLabel(video.getVideoLabel());
				question.setCategory(video.getCategory());
				question.setQuestionTime(new Date());
				question.setIntegral(Integer.parseInt(request
						.getParameter("integral")));
				question.setQuestionName(request.getParameter("quest"));
				question.setIsSolve("noSolve");
				video.getQuestion().add(question);
				videoDao.update(video);
			}
			return null;
		}else{
			request.setAttribute("deall", "2");
			return "BackLogin";
		}
	}
	
	//更新视频信息
	public String videoUpDate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Video video = videoDao.findById(Integer.parseInt(request
				.getParameter("videoId")));

		video.setVideoAuthor(request.getParameter("videoAuthor"));
		video.setVideoName(request.getParameter("videoName"));
		video.setVideoLabel(request.getParameter("videoLabel"));
		video.setStudyNum(Integer.parseInt(request.getParameter("studyNum_1")));
		video.setCourseIntro(request.getParameter("intro"));
		video.setUpdateTime(date);
		video.setCategory(request.getParameter("category"));
		videoDao.update(video);
		return "videoUpDate";
			
	}
	
	//视频信息编辑
	public void AdminVideoEdit() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String videoId = request.getParameter("videoId");
		Video video1 = videoDao.findById(Integer.parseInt(videoId));
			    
		//从数据库得到video对象存到request
		request.setAttribute("video1", video1);
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/admin/video/videoEdit.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * 分页展示视频
	 * @return
	 * @throws Exception
	 */
	// 视频分页展示
	public String ShowAllVideoByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String label = null;
		String category = null;
		if (request.getParameter("label") != null) {
			label = new String(request.getParameter("label").getBytes("ISO8859-1"),"UTF-8");
			request.setAttribute("label", label);
		}
		if (request.getParameter("category") != null) {
			category = new String(request.getParameter("category").getBytes("ISO8859-1"),"UTF-8");
			request.setAttribute("category", category);
		}
		String orderBy = request.getParameter("orderby");
		//如果当前页码小于1，则赋值为1，即首页
		if(currentPage <= 0) {
			 currentPage = 1;
		}
		String order = null;
		if(orderBy != null){
			if(orderBy.equals("studyNum")){
				order = new Help().getZuire();
			}
			if(orderBy.equals("updateTime")){
				order = new Help().getZuixin();
			}
		}else{
			order = new Help().getZuixin();
		}
		request.setAttribute("order", order);
		//获取总页数
		int totalPage=videoDao.getVideoTotal(pageSize, category, label);
		//分页查询数据
		List<Video> resource = videoDao.getAllVideoByPage(currentPage,
				pageSize, category, label, orderBy);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "listAllVideo";
	}
	public void ShowAdminVideo() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
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
		String orderBy = new Help().getZuire();
		// 如果当前页码小于1，则赋值为1，即首页
		if (currentPage <= 0) {
			currentPage = 1;
		}
		int totalPage = videoDao.getVideoTotal(4, category, label);
		List<Video> resource = videoDao.getAllVideoByPage(currentPage, 4,
				category, label, orderBy);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/admin/video/video_list.jsp");
		dispatcher.forward(request, response);
	}

	// 搜索分页查询资源
	public String sreachVideoByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();

		String search = request.getParameter("search");
		if (search != null) {
			// if (request.getParameter("search") != null) {
			// search = new String(request.getParameter("search").getBytes(
			// "ISO8859-1"), "UTF-8");
			request.setAttribute("search", search);
			// }
			System.out.println(search);
			// 如果当前页码小于1，则赋值为1，即首页
			if (currentPage <= 0) {
				currentPage = 1;
			}
			// 获取总页数
			int totalPage = videoDao.searchVideoTotal(4, search);
			// 分页查询数据
			List<Video> resource = videoDao.searchVideoByPage(currentPage, 4,
					search);
			new ActionHelp().getShowPage(request, totalPage, currentPage,
					resource);
			return "showVideo";
		} else {
			return null;
		}
	}

}
