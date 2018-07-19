package com.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UserVideo implements Serializable {
	private static final long serialVersionUID = 3199202252805556212L;

	/** identifier field */
    private Integer userVideoId;

    /** nullable persistent field */
    private String videoName;

    /** nullable persistent field */
    private String videoUser;

    /** nullable persistent field */
    private String videoType;

    /** nullable persistent field */
    private String videoPath;

    /** nullable persistent field */
    private String label;

    /** nullable persistent field */
    private String category;

    /** nullable persistent field */
    private String download;

    /** nullable persistent field */
    private String collent;

    /** nullable persistent field */
    private String study;

    /** nullable persistent field */
    private String allTime;

    /** nullable persistent field */
    private String currentTime;

    /** nullable persistent field */
    private String videoAuthor;

    /** nullable persistent field */
    private com.model.Video video;

    /** full constructor */
    public UserVideo(String videoName, String videoUser, String videoType, String videoPath, String label, String category, String download, String collent, String study, String allTime, String currentTime, String videoAuthor, com.model.Video video) {
        this.videoName = videoName;
        this.videoUser = videoUser;
        this.videoType = videoType;
        this.videoPath = videoPath;
        this.label = label;
        this.category = category;
        this.download = download;
        this.collent = collent;
        this.study = study;
        this.allTime = allTime;
        this.currentTime = currentTime;
        this.videoAuthor = videoAuthor;
        this.video = video;
    }

    /** default constructor */
    public UserVideo() {
    }

    public Integer getUserVideoId() {
        return this.userVideoId;
    }

    public void setUserVideoId(Integer userVideoId) {
        this.userVideoId = userVideoId;
    }

    public String getVideoName() {
        return this.videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUser() {
        return this.videoUser;
    }

    public void setVideoUser(String videoUser) {
        this.videoUser = videoUser;
    }

    public String getVideoType() {
        return this.videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getVideoPath() {
        return this.videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
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

    public String getDownload() {
        return this.download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getCollent() {
        return this.collent;
    }

    public void setCollent(String collent) {
        this.collent = collent;
    }

    public String getStudy() {
        return this.study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getAllTime() {
        return this.allTime;
    }

    public void setAllTime(String allTime) {
        this.allTime = allTime;
    }

    public String getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getVideoAuthor() {
        return this.videoAuthor;
    }

    public void setVideoAuthor(String videoAuthor) {
        this.videoAuthor = videoAuthor;
    }

    public com.model.Video getVideo() {
        return this.video;
    }

    public void setVideo(com.model.Video video) {
        this.video = video;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userVideoId", getUserVideoId())
            .toString();
    }

}
