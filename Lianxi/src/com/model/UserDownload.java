package com.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UserDownload implements Serializable {
	private static final long serialVersionUID = 2199393118266909609L;

	/** identifier field */
    private Integer downloadId;

    /** nullable persistent field */
    private String downName;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private Date downloadTime;

    /** nullable persistent field */
    private String uploadUser;

    /** nullable persistent field */
    private Integer integral;

    /** nullable persistent field */
    private String category;

    /** nullable persistent field */
    private String label;

    /** nullable persistent field */
    private String downUser;

	private com.model.Resource resource;

    /** full constructor */
	public UserDownload(String downName, String type, Date downloadTime,
			String uploadUser, Integer integral, String category, String label,
			String downUser, Resource resource) {
        this.downName = downName;
        this.type = type;
        this.downloadTime = downloadTime;
        this.uploadUser = uploadUser;
        this.integral = integral;
        this.category = category;
        this.label = label;
        this.downUser = downUser;
		this.setResource(resource);
    }

    /** default constructor */
    public UserDownload() {
    }

    public Integer getDownloadId() {
        return this.downloadId;
    }

    public void setDownloadId(Integer downloadId) {
        this.downloadId = downloadId;
    }

    public String getDownName() {
        return this.downName;
    }

    public void setDownName(String downName) {
        this.downName = downName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDownloadTime() {
        return this.downloadTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }

    public String getUploadUser() {
        return this.uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public Integer getIntegral() {
        return this.integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDownUser() {
        return this.downUser;
    }

    public void setDownUser(String downUser) {
        this.downUser = downUser;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("downloadId", getDownloadId())
            .toString();
    }

	public com.model.Resource getResource() {
		return resource;
	}

	public void setResource(com.model.Resource resource) {
		this.resource = resource;
	}

}
