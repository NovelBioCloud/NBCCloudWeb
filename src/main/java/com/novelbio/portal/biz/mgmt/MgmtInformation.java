package com.novelbio.portal.biz.mgmt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.novelbio.portal.biz.model.Information;
import com.novelbio.portal.biz.repo.RepoInformation;

@Service
public class MgmtInformation {
	@Autowired
	RepoInformation repoInformation;

	public List<Information> getList() {
		return Lists.newArrayList(repoInformation.findAll());
	}

	public Information create(String key, String name, String value) {
		Information info = new Information();
		info.setKey(key);
		info.setName(name);
		info.setValue(value);
		return repoInformation.save(info);
	}

	public Information findOneByKey(String key) {
		return repoInformation.findOneByKey(key);
	}

	public Information update(String id, String key, String name, String value) {
		Information info = repoInformation.findOne(id);
		info.setKey(key);
		info.setName(name);
		info.setValue(value);
		return repoInformation.save(info);
	}

	public void remove(String id) {
		repoInformation.delete(id);
	}

	public void removeByKey(String key) {
		repoInformation.delete(repoInformation.findOneByKey(key));
	}
}
