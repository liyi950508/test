package com.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Admin implements Serializable {
	private static final long serialVersionUID = -2465841848454114012L;

    private Integer adminId;

    private String adminName;

    private String adminPassword;

    public Admin(String adminName, String adminPassword) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
    }

    public Admin() {
    }

    public Integer getAdminId() {
        return this.adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return this.adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return this.adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("adminId", getAdminId())
            .toString();
    }

}
