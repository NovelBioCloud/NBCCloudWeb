package com.novelbio.portal.biz.home.mgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelbio.portal.biz.application.domain.Culture;
import com.novelbio.portal.biz.application.repo.DataSource;

@Service
public class MgmtCulture {

	@Autowired
	DataSource dataSource;

	public void update(String coporationCulture, String environment, String activity) {
		dataSource.doTransaction(db -> {
			Culture culture = db.getCulture();
			culture.setCoporationCulture(coporationCulture);
			culture.setEnvironment(environment);
			culture.setActivity(activity);
		});
	}

	public Culture getCulture() {
		return dataSource.getCulture();
	}
}
