package com.novelbio.portal.biz.entity;

import org.apache.commons.lang3.StringUtils;

import com.novelbio.portal.biz.model.User;

public class UserEntity extends User {
	public UserEntity(User user) {
		super();
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
	}

	public boolean isLogin() {
		return StringUtils.equals(this.getUsername(), "admin")
				&& StringUtils.equals(this.getPassword(), "admin@novelbio.com");
	}
}
