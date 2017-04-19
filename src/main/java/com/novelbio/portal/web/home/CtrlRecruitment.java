package com.novelbio.portal.web.home;

import org.springframework.stereotype.Controller;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.biz.home.mgmt.MgmtRecruitment;

@Controller("recruitment")
public class CtrlRecruitment extends AbstractController {
	MgmtRecruitment mgmtRecruitment;

}
