package com.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class User implements Serializable {
	private static final long serialVersionUID = 7388403830910901065L;

    private Integer id;

    private String username;

    private String password;

    private String sex;

    private String userJob;

	private Integer integral = 0;

	private Set<UserNew> userNew = new HashSet<UserNew>();

	public User(String username, String password, String sex, String userJob,
			Integer integral, Set<UserNew> userNew) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.userJob = userJob;
        this.integral = integral;
		this.setUserNew(userNew);
    }

    public User() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserJob() {
        return this.userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    public Integer getIntegral() {
        return this.integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set<UserNew> getUserNew() {
		return userNew;
	}


	public void setUserNew(Set<UserNew> userNew) {
		this.userNew = userNew;
	}

}
