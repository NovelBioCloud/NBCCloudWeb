package com.novelbio.portal.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.biz.mgmt.MgmtCulture;
import com.novelbio.portal.biz.mgmt.MgmtHistory;
import com.novelbio.portal.biz.mgmt.MgmtRecruitment;

@Controller
@RequestMapping("admin")
public class CtrlAdmin extends AbstractController {

	@Autowired
	MgmtHistory mgmtHistory;
	@Autowired
	MgmtRecruitment mgmtRecruitment;
	@Autowired
	MgmtCulture mgmtCulture;

	@RequestMapping
	String index() {
		return "core-module/home/html/admin";
	}

}
