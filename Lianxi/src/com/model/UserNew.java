package com.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class UserNew implements Serializable {
	private static final long serialVersionUID = -1880655395511858908L;

	private Integer newsId;

    private String userName;

    private String newsName;

    private Date newsTime;

	private String newsRead;

	public UserNew(Integer newsId, String userName, String newsName,
			Date newsTime, String newsRead) {
        this.newsId = newsId;
        this.userName = userName;
        this.newsName = newsName;
        this.newsTime = newsTime;
		this.newsRead = newsRead;
    }

    public UserNew() {
    }

    public UserNew(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getNewsId() {
        return this.newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewsName() {
        return this.newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public Date getNewsTime() {
        return this.newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

	public String getNewsRead() {
		return this.newsRead;
	}

	public void setNewsRead(String newsRead) {
		this.newsRead = newsRead;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("newsId", getNewsId())
            .toString();
    }

}
