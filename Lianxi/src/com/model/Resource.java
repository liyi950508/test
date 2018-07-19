package com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Resource implements Serializable {
	private static final long serialVersionUID = -6106599177395592250L;

	/** identifier field */
    private Integer resourceId;

    /** nullable persistent field */
    private String resourceName;

    /** nullable persistent field */
    private String resourceType;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private String label;

    /** nullable persistent field */
    private String intro;

    /** nullable persistent field */
    private String uploadUser;
    
    private String isExamine;

	public String getIsExamine() {
		return isExamine;
	}

	public void setIsExamine(String isExamine) {
		this.isExamine = isExamine;
	}

	/** nullable persistent field */
    private Date uploadTime;

    /** nullable persistent field */
	private Integer integral = 0;

    /** nullable persistent field */
	private Integer downloadNumber = 0;

    /** nullable persistent field */
	private Integer browseNumber = 0;

    /** nullable persistent field */
	private Integer collectNumber = 0;

    /** nullable persistent field */
    private String category;

    /** nullable persistent field */
	private Integer commentaryNumber = 0;

    /** nullable persistent field */
    private String resourcePath;

    /** nullable persistent field */
    private com.model.UserSelect userSelect;

    /** nullable persistent field */
    private com.model.UserDownload userDownload;

    /** persistent field */
	private Set<Comment> comment = new HashSet<Comment>();

    /** full constructor */
	public Resource(String resourceName, String resourceType, String type,
			String label, String intro, String uploadUser, String isExamine,
			Date uploadTime,
			Integer integral, Integer downloadNumber, Integer browseNumber,
			Integer collectNumber, String category, Integer commentaryNumber,
 String resourcePath,
			com.model.UserSelect userSelect,
			com.model.UserDownload userDownload, Set<Comment> comment) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.type = type;
        this.label = label;
        this.intro = intro;
        this.uploadUser = uploadUser;
		this.isExamine = isExamine;
        this.uploadTime = uploadTime;
        this.integral = integral;
        this.downloadNumber = downloadNumber;
        this.browseNumber = browseNumber;
        this.collectNumber = collectNumber;
        this.category = category;
        this.commentaryNumber = commentaryNumber;
        this.resourcePath = resourcePath;
        this.userSelect = userSelect;
        this.userDownload = userDownload;
        this.comment = comment;
    }

    /** default constructor */
    public Resource() {
    }

    /** minimal constructor */
	public Resource(Set<Comment> comment) {
        this.comment = comment;
    }

    public Integer getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUploadUser() {
        return this.uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public Date getUploadTime() {
        return this.uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getIntegral() {
        return this.integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getDownloadNumber() {
        return this.downloadNumber;
    }

    public void setDownloadNumber(Integer downloadNumber) {
        this.downloadNumber = downloadNumber;
    }

    public Integer getBrowseNumber() {
        return this.browseNumber;
    }

    public void setBrowseNumber(Integer browseNumber) {
        this.browseNumber = browseNumber;
    }

    public Integer getCollectNumber() {
        return this.collectNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCommentaryNumber() {
        return this.commentaryNumber;
    }

    public void setCommentaryNumber(Integer commentaryNumber) {
        this.commentaryNumber = commentaryNumber;
    }

    public String getResourcePath() {
        return this.resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public com.model.UserSelect getUserSelect() {
        return this.userSelect;
    }

    public void setUserSelect(com.model.UserSelect userSelect) {
        this.userSelect = userSelect;
    }

    public com.model.UserDownload getUserDownload() {
        return this.userDownload;
    }

    public void setUserDownload(com.model.UserDownload userDownload) {
        this.userDownload = userDownload;
    }

	public Set<Comment> getComment() {
        return this.comment;
    }

	public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("resourceId", getResourceId())
            .toString();
    }

}
