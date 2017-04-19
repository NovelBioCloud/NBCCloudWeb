package com.novelbio.portal.biz.home.mgmt;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelbio.portal.biz.application.domain.History;
import com.novelbio.portal.biz.application.repo.DataSource;

@Service
public class MgmtHistory {

	@Autowired
	DataSource dataSource;

	public History create(Date time, String content) {
		String id = UUID.randomUUID().toString();
		History history = new History(id, time, content);
		dataSource.doTransaction((db) -> {
			db.getListHistory().add(history);
		});
		return this.get(history.getId());
	}

	public void remove(String id) {
		dataSource.doTransaction((db) -> {
			List<History> listHistory = db.getListHistory();
			History history = db.getHistory(id);
			if (history != null) {
				listHistory.remove(history);
			}
		});
	}

	public List<History> list() {
		List<History> result = dataSource.listHistory();
		return result;
	}

	public History get(String id) {
		return dataSource.getHistory(id);
	}

	public void update(String id, Date time, String content) {

		dataSource.doTransaction((db) -> {
			History history = db.getHistory(id);
			if (history != null) {
				db.getListHistory().remove(history);
			}
		});
	}

}
