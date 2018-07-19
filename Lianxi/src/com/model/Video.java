package com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Video implements Serializable {
	private static final long serialVersionUID = 4932307701925071938L;

	/** identifier field */
    private Integer videoId;

    /** nullable persistent field */
    private String videoName;

    /** nullable persistent field */
    private String videoType;

    /** nullable persistent field */
    private String videoLabel;

    /** nullable persistent field */
	private Integer studyNum = 0;

    /** nullable persistent field */
    private String courseIntro;

    /** nullable persistent field */
    private String courseAlltime;

    /** nullable persistent field */
    private String videoPath;

    /** nullable persistent field */
    private String videoAuthor;

    /** nullable persistent field */
    private Date updateTime;

    /** nullable persistent field */
    private String category;

    /** nullable persistent field */
    private com.model.UserVideo userVideo;

    /** persistent field */
	private Set<Question> question = new HashSet<Question>();

    /** full constructor */
	public Video(String videoName, String videoType, String videoLabel,
			Integer studyNum, String courseIntro, String courseAlltime,
			String videoPath, String videoAuthor, Date updateTime,
			String category, com.model.UserVideo userVideo,
			Set<Question> question) {
        this.videoName = videoName;
        this.videoType = videoType;
        this.videoLabel = videoLabel;
        this.studyNum = studyNum;
        this.courseIntro = courseIntro;
        this.courseAlltime = courseAlltime;
        this.videoPath = videoPath;
        this.videoAuthor = videoAuthor;
        this.updateTime = updateTime;
        this.category = category;
        this.userVideo = userVideo;
        this.question = question;
    }

    /** default constructor */
    public Video() {
    }

    /** minimal constructor */
	public Video(Set<Question> question) {
        this.question = question;
    }

    public Integer getVideoId() {
        return this.videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return this.videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoType() {
        return this.videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getVideoLabel() {
        return this.videoLabel;
    }

    public void setVideoLabel(String videoLabel) {
        this.videoLabel = videoLabel;
    }

    public Integer getStudyNum() {
        return this.studyNum;
    }

    public void setStudyNum(Integer studyNum) {
        this.studyNum = studyNum;
    }

    public String getCourseIntro() {
        return this.courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
    }

    public String getCourseAlltime() {
        return this.courseAlltime;
    }

    public void setCourseAlltime(String courseAlltime) {
        this.courseAlltime = courseAlltime;
    }

    public String getVideoPath() {
        return this.videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoAuthor() {
        return this.videoAuthor;
    }

    public void setVideoAuthor(String videoAuthor) {
        this.videoAuthor = videoAuthor;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public com.model.UserVideo getUserVideo() {
        return this.userVideo;
    }

    public void setUserVideo(com.model.UserVideo userVideo) {
        this.userVideo = userVideo;
    }

	public Set<Question> getQuestion() {
        return this.question;
    }

	public void setQuestion(Set<Question> question) {
        this.question = question;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("videoId", getVideoId())
            .toString();
    }

}
