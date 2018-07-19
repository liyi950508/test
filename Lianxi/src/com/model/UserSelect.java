package com.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UserSelect implements Serializable {
	private static final long serialVersionUID = 2585335161763660428L;

	/** identifier field */
    private Integer userselectId;

    /** nullable persistent field */
    private String selectName;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private String uploadUser;

    /** nullable persistent field */
    private Date selectTime;

    /** nullable persistent field */
    private String label;

    /** nullable persistent field */
    private String category;

    /** nullable persistent field */
    private String selectUser;

    /** nullable persistent field */
    private com.model.Resource resource;

    /** full constructor */
    public UserSelect(String selectName, String type, String uploadUser, Date selectTime, String label, String category, String selectUser, com.model.Resource resource) {
        this.selectName = selectName;
        this.type = type;
        this.uploadUser = uploadUser;
        this.selectTime = selectTime;
        this.label = label;
        this.category = category;
        this.selectUser = selectUser;
        this.resource = resource;
    }

    /** default constructor */
    public UserSelect() {
    }

    public Integer getUserselectId() {
        return this.userselectId;
    }

    public void setUserselectId(Integer userselectId) {
        this.userselectId = userselectId;
    }

    public String getSelectName() {
        return this.selectName;
    }

    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUploadUser() {
        return this.uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public Date getSelectTime() {
        return this.selectTime;
    }

    public void setSelectTime(Date selectTime) {
        this.selectTime = selectTime;
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

    public String getSelectUser() {
        return this.selectUser;
    }

    public void setSelectUser(String selectUser) {
        this.selectUser = selectUser;
    }

    public com.model.Resource getResource() {
        return this.resource;
    }

    public void setResource(com.model.Resource resource) {
        this.resource = resource;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userselectId", getUserselectId())
            .toString();
    }

}
