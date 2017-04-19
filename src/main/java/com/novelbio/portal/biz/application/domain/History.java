package com.novelbio.portal.biz.application.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.annotation.JSONField;

public class History implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	@JSONField(format = "yyyy-MM-dd")
	private Date time;
	private String content;

	public History() {

	}

	public History(String id, Date time, String content) {
		super();
		this.id = id;
		this.time = time;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public History clone() {
		History temp = new History();
		BeanUtils.copyProperties(this, temp);
		return temp;
	}

}
