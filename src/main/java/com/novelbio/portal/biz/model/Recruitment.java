package com.novelbio.portal.biz.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.alibaba.fastjson.annotation.JSONField;

@Document
public class Recruitment implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String name;
	private String type;
	private int number;
	private String description;
	@JSONField(format = "yyyy-MM-dd")
	private Date publishTime;
	private String link;
	private String workLocation;

	public Recruitment() {
		super();
	}

	public Recruitment(String id, String name, String type, int number, String description, Date publishTime,
			String link, String workLocation) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.number = number;
		this.description = description;
		this.publishTime = publishTime;
		this.link = link;
		this.workLocation = workLocation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public Recruitment clone() {
		Recruitment temp = new Recruitment();
		BeanUtils.copyProperties(this, temp);
		return temp;
	}
}
