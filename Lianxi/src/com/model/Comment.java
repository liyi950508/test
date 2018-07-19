package com.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Comment implements Serializable {
	private static final long serialVersionUID = -1170366015960517148L;

	/** identifier field */
    private Integer commentsId;

    /** nullable persistent field */
    private String commentsName;

    /** nullable persistent field */
    private String commmetsUser;

    /** nullable persistent field */
    private Date commentsTime;

    /** full constructor */
    public Comment(String commentsName, String commmetsUser, Date commentsTime) {
        this.commentsName = commentsName;
        this.commmetsUser = commmetsUser;
        this.commentsTime = commentsTime;
    }

    /** default constructor */
    public Comment() {
    }

    public Integer getCommentsId() {
        return this.commentsId;
    }

    public void setCommentsId(Integer commentsId) {
        this.commentsId = commentsId;
    }

    public String getCommentsName() {
        return this.commentsName;
    }

    public void setCommentsName(String commentsName) {
        this.commentsName = commentsName;
    }

    public String getCommmetsUser() {
        return this.commmetsUser;
    }

    public void setCommmetsUser(String commmetsUser) {
        this.commmetsUser = commmetsUser;
    }

    public Date getCommentsTime() {
        return this.commentsTime;
    }

    public void setCommentsTime(Date commentsTime) {
        this.commentsTime = commentsTime;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("commentsId", getCommentsId())
            .toString();
    }

}
