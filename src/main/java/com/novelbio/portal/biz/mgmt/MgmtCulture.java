package com.novelbio.portal.biz.mgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.novelbio.portal.biz.model.Culture;
import com.novelbio.portal.biz.repo.RepoCulture;

@Service
public class MgmtCulture {
	@Autowired
	RepoCulture daoCulture;

	public void update(String id, String coporationCulture, String environment, String activity) {
		Culture culture = daoCulture.findOne(id);
		culture.setCoporationCulture(coporationCulture);
		culture.setEnvironment(environment);
		culture.setActivity(activity);
		daoCulture.save(culture);
	}

	public Culture getCulture() {
		return Lists.newArrayList(daoCulture.findAll()).stream().findFirst().orElse(null);
	}

	public Culture getCulture(String id) {
		return daoCulture.findOne(id);
	}
}
