package com.novelbio.portal.biz.mgmt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.novelbio.portal.biz.model.CloudFile;
import com.novelbio.portal.biz.repo.RepoCloudFile;

@Service
public class MgmtCloudFile {
	@Autowired
	RepoCloudFile repoCloudFile;

	public void update(String id, String name, String path, String link, String description) {
		CloudFile file = repoCloudFile.findOne(id);
		file.setName(name);
		file.setPath(path);
		file.setLink(link);
		file.setDescription(description);
		repoCloudFile.save(file);
	}

	public CloudFile create(String name, String path, String link, String description) {
		CloudFile file = new CloudFile();
		file.setName(name);
		file.setPath(path);
		file.setLink(link);
		file.setDescription(description);
		return repoCloudFile.save(file);
	}

	public void remove(String id) {
		repoCloudFile.delete(id);
	}

	public List<CloudFile> list() {
		return Lists.newArrayList(repoCloudFile.findAll().iterator());
	}

	public CloudFile get(String id) {
		return repoCloudFile.findOne(id);
	}
}
