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

	private File uploadFile;		 	 // �û��ϴ����ļ�
    private String uploadFileFileName;  // �ϴ��ļ����ļ���
    private String uploadFileContentType;// �ϴ��ļ�������
    private InputStream inputStream;     //��Դ������
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

	private int currentPage=1;//��ǰҳ��
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

	private String contentDisposition;
	public String getContentDisposition() {
		return contentDisposition;
	}
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	
	//�ϴ���Դ��������Դ��Ϣ�洢�����ݿ�
	public String uploadResource() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		//��ȡ��ǰ�û�
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
			//�õ�����Ŀ¼
			String Path = "E:\\upload\\";
			//�����ļ���uploadFileFileName���ݿ��ѯ
			Resource resoucename = resourceDao.getResource(uploadFileFileName, null, null, null);
			//��ȡ�ϴ��ļ��ĸ�ʽ
			String extention = uploadFileFileName.substring(uploadFileFileName.lastIndexOf(".")+1);
		    
			//�����ļ��ϴ���ʽ
			if(extention.equals("pdf")||extention.equals("doc")||extention.equals("txt")||
					extention.equals("zip")||extention.equals("tar")||extention.equals("ppt")) {
				if(uploadFile != null && resoucename == null) {
					//�洢·��
					String savePath = Path + uploadFileFileName;
					
					//�����ļ�
					File saveFile = new File(savePath);
					uploadFile.renameTo(saveFile);
					
					Resource resource = new Resource();
					//��resource����ֵ
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
	
	// ����Ա���
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
			// ɾ���������ϵ��ļ�
			String path = ServletActionContext.getServletContext().getRealPath(
					"");
			String resourcePath = path + "\\" + res.getResourcePath();
			File file = new File(resourcePath);
			file.delete();
			// ɾ�����ݱ��¼
			resourceDao.delete(res);
		}
		RequestDispatcher dis = request
				.getRequestDispatcher("jsp/admin/resource/resource_Nolist.jsp");
		dis.forward(request, response);
	}

	//������Դ
	public String DownloadResource() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = (User) request.getSession().getAttribute("user");
		// ��ҳ���������ӻ�ȡ�ļ�����
		String fileName = request.getParameter("fileName");
		String downFileName = new String(fileName.getBytes("ISO8859-1"),
				"UTF-8");
		Resource resource1 = resourceDao.getResource(downFileName, null, null,
				null);
		if (user != null) {
			if (user.getIntegral() >= resource1.getIntegral()) {
				/**�ļ�����**/
				File file = new File(resource1.getResourcePath());
				inputStream = new FileInputStream(file);
				// ���û�������С
				response.setBufferSize(2048);
				// �����ļ����Ͷ���
				response.setContentType("application/octet-stream");
				// ���������ļ�����ʽ
				setContentDisposition("attachment;filename="
						+ URLEncoder.encode(downFileName, "UTF-8"));
				// �����ļ����������
				setInputStream(inputStream);

				// **��ֵ����**
				resource1
						.setDownloadNumber((resource1.getDownloadNumber() + 1));
				user.setIntegral(user.getIntegral() - resource1.getIntegral());
				User user1 = userDao.getUser(resource1.getUploadUser());
				user1.setIntegral(user1.getIntegral() + resource1.getIntegral());

				resourceDao.update(resource1);
				userDao.update(user);
				userDao.update(user1);
				// **�����ص���Դ�洢����Դ���ر�**
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
	 * �ղ���Դ
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
	    	//**������Դ�ղ�����**
	    	int resourceCollentNum = resource.getCollectNumber();
	    	resource.setCollectNumber(resourceCollentNum + 1);
	    	
			// ��ȡ��һҳurl·��
			// String url = request.getHeader("referer");

	    	//**���ղص���Դ�洢����Դ�ղر�**
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
  
	//�ύ����
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

	//�鿴��Դ�������
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
	//ɾ����Դ
	public void AdminDeleteResource() throws ServletException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String resourceId = request.getParameter("resourceId");	
		Resource resource = resourceDao.findById(Integer.parseInt(resourceId));
		//ɾ���������ϵ��ļ�
		String path = ServletActionContext.getServletContext().getRealPath("");
		String resourcePath = path+"\\"+resource.getResourcePath();
		File file = new File(resourcePath);
		file.delete();
		//ɾ�����ݱ��¼
		resourceDao.delete(resource);
		RequestDispatcher dis = request
				.getRequestDispatcher("jsp/admin/resource/resource_list.jsp");
		dis.forward(request, response);
	}
	/**
	 * ��ҳչʾ��Դ
	 * @return
	 * @throws Exception
	 */
	//��Դ��ҳչʾ(ȫ��)
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
		
		//�����ǰҳ��С��1����ֵΪ1������ҳ
		if(currentPage <= 0) {
			 currentPage = 1;
		}
		//��ȡ��ҳ��
		int totalPage = resourceDao.getAllTotal(pageSize, label, isExamine);
		//��ҳ��ѯ����
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
	//��Դ��ҳչʾ(����ѧϰ)
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
	//��Դ��ҳչʾ(ģʽʶ��)
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
		//�����ǰҳ��С��1����ֵΪ1������ҳ
		if(currentPage <= 0) {
			 currentPage = 1;
		}
		//��ȡ��ҳ��
		int totalPage = resourceDao.getTotal(pageSize, null, null, label, category,type);
		List<Resource> resource = resourceDao.getResourceByPage(currentPage, pageSize, null, null, label, category, type, orderBy);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "showMSSbByPage";
	}
	//��ѯ��Դ����ҳչʾ���ϴ��ߣ�
	public String showUpUserByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		User userUser = (User) request.getSession().getAttribute("user");
		//�����ǰҳ��С��1����ֵΪ1������ҳ
		if(currentPage <= 0) {
			 currentPage = 1;
		}
		int totalPage = resourceDao.getTotal(pageSize, null, userUser.getUsername(), null, null, null);
		List<Resource> resource = resourceDao.getResourceByPage(currentPage, pageSize, null, userUser.getUsername(), null,null, null, null);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "showUpUserByPage";
	}
	// ������ҳ��ѯ��Դ
	public String sreachResByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();

		String search = null;
		if (request.getParameter("search") != null) {
			search = new String(request.getParameter("search").getBytes(
					"ISO8859-1"), "UTF-8");
			request.setAttribute("search", search);
		}

		// �����ǰҳ��С��1����ֵΪ1������ҳ
		if (currentPage <= 0) {
			currentPage = 1;
		}
		// ��ȡ��ҳ��
		int totalPage = resourceDao.searchResTotal(10, search);
		// ��ҳ��ѯ����
		List<Resource> resource = resourceDao.searchResByPage(currentPage, 6,
				search);
		new ActionHelp().getShowPage(request, totalPage, currentPage, resource);
		return "showResource";
	}
}