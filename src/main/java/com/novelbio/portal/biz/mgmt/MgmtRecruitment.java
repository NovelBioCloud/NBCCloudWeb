package com.novelbio.portal.biz.mgmt;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.novelbio.portal.biz.model.Recruitment;
import com.novelbio.portal.biz.repo.RepoRecruitment;

@Service
public class MgmtRecruitment {
	@Autowired
	RepoRecruitment repoRecruitment;

	public Recruitment create(String name, String type, int number, String description, Date publishTime, String link,
			String workLocation) {

		String id = UUID.randomUUID().toString();
		Recruitment recruitment = new Recruitment(id, name, type, number, description, publishTime, link, workLocation);
		return repoRecruitment.save(recruitment);
	}

	public void remove(String id) {
		repoRecruitment.delete(id);
	}

	public List<Recruitment> getList() {
		return Lists.newArrayList(repoRecruitment.findAll());
	}

	public Recruitment get(String id) {
		return repoRecruitment.findOne(id);
	}

	public void update(String id, String name, String type, int number, String description, Date publishTime,
			String link, String workLocation) {
		Recruitment recruitment = repoRecruitment.findOne(id);
		recruitment.setName(name);
		recruitment.setType(type);
		recruitment.setNumber(number);
		recruitment.setDescription(description);
		recruitment.setPublishTime(publishTime);
		recruitment.setLink(link);
		recruitment.setWorkLocation(workLocation);
		repoRecruitment.save(recruitment);
	}
}
