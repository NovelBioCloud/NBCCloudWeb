package com.novelbio.portal.biz.model;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Culture implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	String id;
	String coporationCulture;
	String environment;
	String activity;

	public Culture() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Culture(String coporationCulture, String environment, String activity) {
		super();
		this.coporationCulture = coporationCulture;
		this.environment = environment;
		this.activity = activity;
	}

	public String getCoporationCulture() {
		return coporationCulture;
	}

	public void setCoporationCulture(String coporationCulture) {
		this.coporationCulture = coporationCulture;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Override
	public Culture clone() {
		Culture temp = new Culture();
		BeanUtils.copyProperties(this, temp);
		return temp;
	}

}
