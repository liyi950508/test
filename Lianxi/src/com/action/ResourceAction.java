package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
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
import com.dao.user.UserDao;
import com.help.ActionHelp;
import com.help.Help;
import com.model.Comment;
import com.model.Resource;
import com.model.User;
import com.model.UserDownload;
import com.model.UserSelect;
import com.opensymphony.xwork2.ActionSupport;

public class ResourceAction extends ActionSupport {
	private static final long serialVersionUID = 8252883011981727386L;

	Date date = new Date();
	private UserDao userDao;
	private Resource resource;
	private ResourceDao resourceDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public ResourceDao getResourceDao() {
		return resourceDao;
	}
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
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

	private String contentDisposition;
	public String getContentDisposition() {
		return contentDisposition;
	}
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	
	//上传资源，并将资源信息存储到数据库
	public String uploadResource() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		//获取当前用户
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
			//得到保存目录
			String Path = "E:\\upload\\";
			//根据文件名uploadFileFileName数据库查询
			Resource resoucename = resourceDao.getResource(uploadFileFileName, null, null, null);
			//获取上传文件的格式
			String extention = uploadFileFileName.substring(uploadFileFileName.lastIndexOf(".")+1);
		    
			//限制文件上传格式
			if(extention.equals("pdf")||extention.equals("doc")||extention.equals("txt")||
					extention.equals("zip")||extention.equals("tar")||extention.equals("ppt")) {
				if(uploadFile != null && resoucename == null) {
					//存储路径
					String savePath = Path + uploadFileFileName;
					
					//保存文件
					File saveFile = new File(savePath);
					uploadFile.renameTo(saveFile);
					
					Resource resource = new Resource();
					//给resource对象赋值
					resource.setResourceName(uploadFileFileName);
					resource.setResourceType(uploadFileContentType);
					resource.setType(request.getParameter("resource.type"));
					resource.setUploadTime(date);
					resource.setResourcePath(savePath);
					resource.setBrowseNumber(resource.getBrowseNumber());
					resource.setDownloadNumber(resource.getDownloadNumber());
					resource.setCollectNumber(resource.getCollectNumber());
					resource.setCommentaryNumber(resource.getCommentaryNumber());
					resource.setCategory(request
							.getParameter("resource.category"));
					resource.setIntro(request.getParameter("resource.intro"));
					resource.setLabel(request.getParameter("resource.label"));
					resource.setIntegral(Integer.parseInt(request
							.getParameter("integral")));
					resource.setUploadUser(user.getUsername());
					resource.setIsExamine("no");
					user.setIntegral(user.getIntegral() + 5);
					userDao.update(user);
					resourceDao.save(resource);
					request.setAttribute("deall", "8");
					return "upSuccess";
				} else {
					request.setAttribute("deall", "9");
					return "resourceNameError";	
				}
			} else {
				request.setAttribute("deall", "10");
				return "extentionError";
			}
		}else
			return "BackLogin";
	}
	
	// 管理员审核
	public void adminExamine() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		int res_id = Integer.parseInt(request.getParameter("resource_id"));
		Resource res = resourceDao.findById(res_id);
		String isExamine = request.getParameter("isExamine");
		if (isExamine.equals("is")) {
			User user = userDao.getUser(res.getUploadUser());
			res.setIsExamine("is");
			resourceDao.update(res);
			user.setIntegral(user.getIntegral() + 5);
			userDao.update(user);
		}
		if (isExamine.equals("no")) {
			// 删除服务器上的文件
			String path = ServletActionContext.getServletContext().getRealPath(
					"");
			String resourcePath = path + "\\" + res.getResourcePath();
			File file = new File(resourcePath);
			file.delete();
			// 删除数据表记录
			resourceDao.delete(res);
		}
		RequestDispatcher dis = request
				.getRequestDispatcher("jsp/admin/resource/resource_Nolist.jsp");
		dis.forward(request, response);
	}

	//下载资源
	public String DownloadResource() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = (User) request.getSession().getAttribute("user");
		// 从页面下载链接获取文件对象
		String fileName = request.getParameter("fileName");
		String downFileName = new String(fileName.getBytes("ISO8859-1"),
				"UTF-8");
		Resource resource1 = resourceDao.getResource(downFileName, null, null,
				null);
		if (user != null) {
			if (user.getIntegral() >= resource1.getIntegral()) {
				/**文件下载**/
				File file = new File(resource1.getResourcePath());
				inputStream = new FileInputStream(file);
				// 设置缓冲区大小
				response.setBufferSize(2048);
				// 下载文件类型定义
				response.setContentType("application/octet-stream");
				// 设置下载文件处理方式
				setContentDisposition("attachment;filename="
						+ URLEncoder.encode(downFileName, "UTF-8"));
				// 下载文件输出流定义
				setInputStream(inputStream);

				// **数值计算**
				resource1
						.setDownloadNumber((resource1.getDownloadNumber() + 1));
				user.setIntegral(user.getIntegral() - resource1.getIntegral());
				User user1 = userDao.getUser(resource1.getUploadUser());
				user1.setIntegral(user1.getIntegral() + resource1.getIntegral());

				resourceDao.update(resource1);
				userDao.update(user);
				userDao.update(user1);
				// **将下载的资源存储到资源下载表**
				UserDownload userDownload = resource1.getUserDownload();
				UserDownload userDown = new UserDownload();
				if (userDownload == null) {
					userDown.setDownName(resource1.getResourceName());
					userDown.setType(resource1.getResourceType());
					userDown.setUploadUser(resource1.getUploadUser());
					userDown.setLabel(resource1.getLabel());
					userDown.setCategory(resource1.getCategory());
					userDown.setDownloadTime(date);
					userDown.setDownUser(user.getUsername());
					resource1.setUserDownload(userDown);
					resourceDao.update(resource1);
				} else {
					String userDownName = userDownload.getDownName();
					String downUser = userDownload.getUploadUser();
					if (!userDownName.equals(downFileName)
							&& !downUser.equals(user.getUsername())) {
						userDown.setDownName(resource1.getResourceName());
						userDown.setType(resource1.getResourceType());
						userDown.setUploadUser(resource1.getUploadUser());
						userDown.setLabel(resource1.getLabel());
						userDown.setCategory(resource1.getCategory());
						userDown.setDownloadTime(date);
						userDown.setDownUser(user.getUsername());
						resource1.setUserDownload(userDown);
						resourceDao.update(resource1);
					}
				}
				return "downResourceSuccess";
			} else {
				request.setAttribute("deall", "11");
				return "NotIntegral";
			}
		} else {
			return "BackLogin";
		}
	}
	
	/**
	 * 收藏资源
	 * @return
	 * @throws Exception
	 */
	public String CollentResource() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "BackLogin";
		} else {
    		String celectUserName = user.getUsername();
    		
    		String resourceName = request.getParameter("fileName");
	    	String downFileName = new String(resourceName.getBytes("ISO8859-1"),"UTF-8");
	    	Resource resource = resourceDao.getResource(downFileName, null, null, null);
	    	//**更新资源收藏数量**
	    	int resourceCollentNum = resource.getCollectNumber();
	    	resource.setCollectNumber(resourceCollentNum + 1);
	    	
			// 获取上一页url路径
			// String url = request.getHeader("referer");

	    	//**将收藏的资源存储到资源收藏表**
	    	UserSelect userselect1 = resource.getUserSelect();
	    	UserSelect userSelect = new UserSelect();
	    	if(userselect1 == null) {
	    		userSelect.setSelectName(resource.getResourceName());
		    	userSelect.setUploadUser(resource.getUploadUser());
		    	userSelect.setLabel(resource.getLabel());
		    	userSelect.setCategory(resource.getCategory());
		    	userSelect.setSelectTime(date);
		    	userSelect.setSelectUser(celectUserName);
		    	resource.setUserSelect(userSelect);
		    	resourceDao.update(resource);
				request.setAttribute("deall", "12");
				return "selectSuccess";
	    	}else{
	    		String selectResourceName = userselect1.getSelectName();
		    	if(!selectResourceName.equals(downFileName)) {
			    	userSelect.setSelectName(resource.getResourceName());
			    	userSelect.setUploadUser(resource.getUploadUser());
			    	userSelect.setLabel(resource.getLabel());
			    	userSelect.setCategory(resource.getCategory());
			    	userSelect.setSelectTime(date);
			    	userSelect.setSelectUser(celectUserName);
			    	resource.setUserSelect(userSelect);
			    	resourceDao.update(resource);
					request.setAttribute("deall", "12");
					return "selectSuccess";
				} else {
					request.setAttribute("deall", "15");
					return "selectError";
				}
	    	}
		}
    }
  
	//提交评论
	public String addComment() {
		HttpServletRequest request = ServletActionContext.getRequest();	
		User user = (User) request.getSession().getAttribute("user");
		if (user.getUsername() != null) {
			Resource resource = resourceDao.findById(Integer.parseInt(request
					.getParameter("resourceID")));
			if (request.getParameter("comment") != null) {
				Comment comment = new Comment();
				comment.setCommentsName(request.getParameter("comment"));
				comment.setCommmetsUser(user.getUsername());
				comment.setCommentsTime(new Date());
				resource.getComment().add(comment);

				user.setIntegral(user.getIntegral() + 1);
				userDao.update(user);
				resourceDao.update(resource);
			}
			return null;
		}else{
			return "BackLogin";
		}
		
	}

	//查看资源相关评论
	@SuppressWarnings("unchecked")
	public void getResByComment() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String fileName = request.getParameter("resourceId");
		Resource res = resourceDao.findById(Integer.parseInt(fileName));

		Iterator<Comment> itr = res.getComment().iterator();
		List<Comment> list = IteratorUtils.toList(itr);
		int begin = (7 * (currentPage - 1));
		int end = 7 * currentPage;
		for (int i = begin; i <= end && i < list.size(); i++) {
			list.get(i);
		}
		new ActionHelp().getShowPage(request,
				new ActionHelp().showPublicByPage(10, list.size()),
				currentPage, list);
		request.setAttribute("res", res);
		RequestDispatcher dispather = request
				.getRequestDispatcher("/jsp/UpAndDownResource/resource_view.jsp");
		dispather.forward(request, response);
	}
	@SuppressWarnings("unchecked")
	public void getAdminResByComment() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String fileName = request.getParameter("resourceId");
		Resource res = resourceDao.findById(Integer.parseInt(fileName));

		Iterator<Comment> itr = res.getComment().iterator();
		List<Comment> list = IteratorUtils.toList(itr);
		int begin = (7 * (currentPage - 1));
		int end = 7 * currentPage;
		for (int i = begin; i <= end && i < list.size(); i++) {
			list.get(i);
		}
		new ActionHelp().getShowPage(request,
				new ActionHelp().showPublicByPage(10, list.size()),
				currentPage, list);
		request.setAttribute("res", res);
		RequestDispatcher dispather = request
				.getRequestDispatcher("/jsp/admin/resource/resource_view.jsp");
		dispather.forward(request, response);
	}
	//删除资源
	public void AdminDeleteResource() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String resourceId = request.getParameter("resourceId");	
		Resource resource = resourceDao.findById(Integer.parseInt(resourceId));
		//删除服务器上的文件
		String path = ServletActionContext.getServletContext().getRealPath("");
		String resourcePath = path+"\\"+resource.getResourcePath();
		File file = new File(resourcePath);
		file.delete();
		//删除数据表记录
		resourceDao.delete(resource);
		RequestDispatcher dis = request
				.getRequestDispatcher("jsp/admin/resource/resource_list.jsp");
		dis.forward(request, response);
	}
	/**
	 * 分页展示资源
	 * @return
	 * @throws Exception
	 */
	//资源分页展示(全部)
	public String showAllByPage() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String label = null;
		String isExamine = "is";
		String orderBy = request.getParameter("orderby");
		if(request.getParameter("label")!=null) {
			label = new String(request.getParameter("label").getBytes("ISO8859-1"),"UTF-8");
			request.setAttribute("label", label);
		}
		String order = null;
		if(orderBy != null){
			if(orderBy.equals("downloadNumber")){
				order = new Help().getZuire();
			}
			if(orderBy.equals("uploadTime")){
				order = new Help().getZuixin();
			}
		}else{
			order = new Help().getZuixin();
		}
		request.setAttribute("order", order);
		
		//如果当前页码小于1，则赋值为1，即首页
		if(currentPage <= 0) {
			 currentPage = 1;
		}
		//获取总页数
		int totalPage = resourceDao.getAllTotal(pageSize, label, isExamine);
		//分页查询数据
		List<Resource> resource = resourceDao.getAllResourceByPage(currentPage,
				pageSize, label, isExamine, orderBy);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "userListRes";
	}
	public String showAdminAllByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String label = null;
		String isExamine = "is";
		String orderBy = request.getParameter("orderby");
		if (request.getParameter("label") != null) {
			label = new String(request.getParameter("label").getBytes(
					"ISO8859-1"), "UTF-8");
			request.setAttribute("label", label);
		}
		String order = null;
		if (orderBy != null) {
			if (orderBy.equals("downloadNumber")) {
				order = new Help().getZuire();
			}
			if (orderBy.equals("uploadTime")) {
				order = new Help().getZuixin();
			}
		} else {
			order = new Help().getZuixin();
		}
		request.setAttribute("order", order);

		if (currentPage <= 0) {
			currentPage = 1;
		}
		int totalPage = resourceDao.getAllTotal(pageSize, label, isExamine);
		List<Resource> resource = resourceDao.getAllResourceByPage(currentPage,
				pageSize, label, isExamine, orderBy);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "adminListRes";
	}
	public String showAdminNoByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String label = null;
		String isExamine = "no";
		String orderBy = request.getParameter("orderby");
		if (request.getParameter("label") != null) {
			label = new String(request.getParameter("label").getBytes(
					"ISO8859-1"), "UTF-8");
			request.setAttribute("label", label);
		}
		String order = null;
		if (orderBy != null) {
			if (orderBy.equals("downloadNumber")) {
				order = new Help().getZuire();
			}
			if (orderBy.equals("uploadTime")) {
				order = new Help().getZuixin();
			}
		} else {
			order = new Help().getZuixin();
		}
		request.setAttribute("order", order);

		if (currentPage <= 0) {
			currentPage = 1;
		}
		int totalPage = resourceDao.getAllTotal(pageSize, label, isExamine);
		List<Resource> resource = resourceDao.getAllResourceByPage(currentPage,
				pageSize, label, isExamine, orderBy);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "adminListNoRes";
	}
	//资源分页展示(机器学习)
	public String showJQXxByPage() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String type = new Help().getJQXX();
		String label = null;
		String category = null;
		String orderBy = request.getParameter("orderby");
		if(request.getParameter("label")!=null) {
			label = new String(request.getParameter("label").getBytes("ISO8859-1"),"UTF-8");
			request.setAttribute("label", label);
		}
		if(request.getParameter("category")!=null) {
			category = new String(request.getParameter("category").getBytes("ISO8859-1"),"UTF-8");
			request.setAttribute("category", category);
		}
		String order = null;
		if(orderBy != null){
			if(orderBy.equals("downloadNumber")){
				order = new Help().getZuire();
			}
			if(orderBy.equals("uploadTime")){
				order = new Help().getZuixin();
			}
		}else{
			order = new Help().getZuixin();
		}
		request.setAttribute("order", order);
		
		if(currentPage <= 0) {
			 currentPage = 1;
		}
		int totalPage = resourceDao.getTotal(pageSize, null, null, label, category,type);
		List<Resource> resource = resourceDao.getResourceByPage(currentPage, pageSize, null, null, label, category, type, orderBy);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "showJQXxByPage";
	}
	//资源分页展示(模式识别)
	public String showMSSbByPage() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String type = new Help().getMSSB();
		String label = null;
		String category = null;
		String orderBy = request.getParameter("orderby");
		if(request.getParameter("label")!=null) {
			label = new String(request.getParameter("label").getBytes("ISO8859-1"),"UTF-8");
			request.setAttribute("label", label);
		}
		if(request.getParameter("category")!=null) {
			category = new String(request.getParameter("category").getBytes("ISO8859-1"),"UTF-8");
			request.setAttribute("category", category);
		}
		
		String order = null;
		if(orderBy != null){
			if(orderBy.equals("downloadNumber")){
				order = new Help().getZuire();
			}
			if(orderBy.equals("uploadTime")){
				order = new Help().getZuixin();
			}
		}else{
			order = new Help().getZuixin();

		}
		request.setAttribute("order", order);
		//如果当前页码小于1，则赋值为1，即首页
		if(currentPage <= 0) {
			 currentPage = 1;
		}
		//获取总页数
		int totalPage = resourceDao.getTotal(pageSize, null, null, label, category,type);
		List<Resource> resource = resourceDao.getResourceByPage(currentPage, pageSize, null, null, label, category, type, orderBy);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "showMSSbByPage";
	}
	//查询资源并分页展示（上传者）
	public String showUpUserByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		User userUser = (User) request.getSession().getAttribute("user");
		//如果当前页码小于1，则赋值为1，即首页
		if(currentPage <= 0) {
			 currentPage = 1;
		}
		int totalPage = resourceDao.getTotal(pageSize, null, userUser.getUsername(), null, null, null);
		List<Resource> resource = resourceDao.getResourceByPage(currentPage, pageSize, null, userUser.getUsername(), null,null, null, null);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "showUpUserByPage";
	}
	// 搜索分页查询资源
	public String sreachResByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();

		String search = null;
		if (request.getParameter("search") != null) {
			search = new String(request.getParameter("search").getBytes(
					"ISO8859-1"), "UTF-8");
			request.setAttribute("search", search);
		}

		// 如果当前页码小于1，则赋值为1，即首页
		if (currentPage <= 0) {
			currentPage = 1;
		}
		// 获取总页数
		int totalPage = resourceDao.searchResTotal(10, search);
		// 分页查询数据
		List<Resource> resource = resourceDao.searchResByPage(currentPage, 6,
				search);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "showResource";
	}
}