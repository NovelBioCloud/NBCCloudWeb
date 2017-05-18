package com.novelbio.portal.biz.mgmt;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.novelbio.portal.biz.model.History;
import com.novelbio.portal.biz.repo.RepoHistory;

@Service
public class MgmtHistory {
	@Autowired
	RepoHistory repoHistory;

	public History create(Date time, String content) {
		History history = new History(time, content);
		return repoHistory.save(history);
	}

	public void remove(String id) {
		repoHistory.delete(id);
	}

	public List<History> list() {
		return Lists.newArrayList(repoHistory.findAll().iterator());
	}

	public History get(String id) {
		return repoHistory.findOne(id);
	}

	public void update(String id, Date time, String content) {
		History history = repoHistory.findOne(id);
		history.setTime(time);
		history.setContent(content);
		repoHistory.save(history);
	}

}
