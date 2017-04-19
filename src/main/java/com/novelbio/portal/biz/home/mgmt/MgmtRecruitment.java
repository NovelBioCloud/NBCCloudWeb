package com.novelbio.portal.biz.home.mgmt;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelbio.portal.biz.application.domain.Recruitment;
import com.novelbio.portal.biz.application.repo.DataSource;

@Service
public class MgmtRecruitment {
	@Autowired
	DataSource dataSource;

	public Recruitment create(String name, String type, int number, String description, Date publishTime, String link,
			String workLocation) {

		String id = UUID.randomUUID().toString();
		Recruitment recruitment = new Recruitment(id, name, type, number, description, publishTime, link, workLocation);
		dataSource.doTransaction((db) -> {
			db.getListRecruitment().add(recruitment);
		});
		return this.get(recruitment.getId());
	}

	public void remove(String id) {
		dataSource.doTransaction((db) -> {
			List<Recruitment> listRecruitment = db.getListRecruitment();
			Recruitment recruitment = db.getRecruitment(id);
			if (recruitment != null) {
				listRecruitment.remove(recruitment);
			}
		});
	}

	public List<Recruitment> list() {
		List<Recruitment> result = dataSource.listRecruitment();
		return result;
	}

	public Recruitment get(String id) {
		return dataSource.getRecruitment(id);
	}

	public void update(String id, String name, String type, int number, String description, Date publishTime,
			String link, String workLocation) {

		dataSource.doTransaction((db) -> {
			Recruitment recruitment = db.getRecruitment(id);
			recruitment.setName(name);
			recruitment.setType(type);
			recruitment.setNumber(number);
			recruitment.setDescription(description);
			recruitment.setPublishTime(publishTime);
			recruitment.setLink(link);
			recruitment.setWorkLocation(workLocation);
		});
	}
}
