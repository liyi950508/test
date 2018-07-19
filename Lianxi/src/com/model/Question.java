package com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Question implements Serializable {
	private static final long serialVersionUID = -6960237604392069131L;

	/** identifier field */
    private Integer questionId;

    /** nullable persistent field */
    private String questionName;

    /** nullable persistent field */
	private Integer answerNum = 0;

    /** nullable persistent field */
    private String questUser;

    /** nullable persistent field */
	private Integer browseNum = 0;

    /** nullable persistent field */
    private Date questionTime;

    /** nullable persistent field */
    private String label;

    /** nullable persistent field */
    private String category;

    /** nullable persistent field */
	private Integer integral = 0;

    /** nullable persistent field */
    private String isSolve;

    /** persistent field */
	private Set<Answer> answer = new HashSet<Answer>();

    /** full constructor */
	public Question(String questionName, Integer answerNum, String questUser,
			Integer browseNum, Date questionTime, String label,
			String category, Integer integral, String isSolve,
			Set<Answer> answer) {
        this.questionName = questionName;
        this.answerNum = answerNum;
        this.questUser = questUser;
        this.browseNum = browseNum;
        this.questionTime = questionTime;
        this.label = label;
        this.category = category;
        this.integral = integral;
        this.isSolve = isSolve;
        this.answer = answer;
    }

    /** default constructor */
    public Question() {
    }

    /** minimal constructor */
	public Question(Set<Answer> answer) {
        this.answer = answer;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return this.questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Integer getAnswerNum() {
        return this.answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public String getQuestUser() {
        return this.questUser;
    }

    public void setQuestUser(String questUser) {
        this.questUser = questUser;
    }

    public Integer getBrowseNum() {
        return this.browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Date getQuestionTime() {
        return this.questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getIntegral() {
        return this.integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getIsSolve() {
        return this.isSolve;
    }

    public void setIsSolve(String isSolve) {
        this.isSolve = isSolve;
    }

	public Set<Answer> getAnswer() {
        return this.answer;
    }

	public void setAnswer(Set<Answer> answer) {
        this.answer = answer;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("questionId", getQuestionId())
            .toString();
    }

}
