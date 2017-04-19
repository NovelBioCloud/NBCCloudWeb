package com.novelbio.portal.biz.application.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public class DB implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<History> listHistory = Lists.newArrayList();
	List<Recruitment> listRecruitment = Lists.newArrayList();
	Culture culture = new Culture();

	public Culture getCulture() {
		return culture;
	}

	public void setCulture(Culture culture) {
		this.culture = culture;
	}

	public List<History> getListHistory() {
		return listHistory;
	}

	public History getHistory(String id) {
		return listHistory.stream().filter(it -> StringUtils.equals(it.getId(), id)).findAny().orElse(null);
	}

	public Recruitment getRecruitment(String id) {
		return listRecruitment.stream().filter(it -> StringUtils.equals(it.getId(), id)).findAny().orElse(null);
	}

	public void setListHistory(List<History> listHistory) {
		this.listHistory = listHistory;
	}

	public List<Recruitment> getListRecruitment() {
		return listRecruitment;
	}

	public void setListRecruitment(List<Recruitment> listRecruitment) {
		this.listRecruitment = listRecruitment;
	}

	public DB clone() {
		DB db = new DB();
		listHistory.forEach(it -> {
			db.listHistory.add(it.clone());
		});
		listRecruitment.forEach(it -> {
			db.listRecruitment.add(it.clone());
		});
		db.culture = db.culture.clone();
		return db;

	}
}
