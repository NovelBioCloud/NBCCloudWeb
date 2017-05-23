package com.novelbio.portal.biz.mgmt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.novelbio.portal.biz.model.CloudFolder;
import com.novelbio.portal.biz.repo.RepoCloudFolder;

@Service
public class MgmtCloudFolder {
	@Autowired
	RepoCloudFolder repoCloudFolder;

	public void update(String id, String name, String path, String description) {
		CloudFolder folder = repoCloudFolder.findOne(id);
		folder.setName(name);
		folder.setPath(path);
		folder.setDescription(description);
		repoCloudFolder.save(folder);
	}

	public CloudFolder create(String name, String path, String description) {
		CloudFolder folder = new CloudFolder();
		folder.setName(name);
		folder.setPath(path);
		folder.setDescription(description);
		return repoCloudFolder.save(folder);
	}

	public void remove(String id) {
		repoCloudFolder.delete(id);
	}

	public List<CloudFolder> list() {
		return Lists.newArrayList(repoCloudFolder.findAll().iterator());
	}

	public CloudFolder get(String id) {
		return repoCloudFolder.findOne(id);
	}
}
