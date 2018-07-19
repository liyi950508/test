package com.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Answer implements Serializable {
	private static final long serialVersionUID = 7597107684917945035L;

	/** identifier field */
    private Integer answerId;

    /** nullable persistent field */
    private String answerName;

    /** nullable persistent field */
    private String answerUser;

    /** nullable persistent field */
    private Date answerTime;

    /** full constructor */
    public Answer(String answerName, String answerUser, Date answerTime) {
        this.answerName = answerName;
        this.answerUser = answerUser;
        this.answerTime = answerTime;
    }

    /** default constructor */
    public Answer() {
    }

    public Integer getAnswerId() {
        return this.answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerName() {
        return this.answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public String getAnswerUser() {
        return this.answerUser;
    }

    public void setAnswerUser(String answerUser) {
        this.answerUser = answerUser;
    }

    public Date getAnswerTime() {
        return this.answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("answerId", getAnswerId())
            .toString();
    }

}
