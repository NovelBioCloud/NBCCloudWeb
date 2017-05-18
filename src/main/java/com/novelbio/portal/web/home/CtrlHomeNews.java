package com.novelbio.portal.web.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.mgmt.MgmtHistory;
import com.novelbio.portal.biz.mgmt.MgmtNews;
import com.novelbio.portal.biz.mgmt.MgmtRecruitment;

@Controller
@RequestMapping(value = "/home/news")
public class CtrlHomeNews extends AbstractController {
	@Autowired
	MgmtNews mgmtNews;
	@Autowired
	MgmtHistory mgmtHistory;
	@Autowired
	MgmtRecruitment mgmtRecruitment;

	@ResponseBody
	@RequestMapping("getList")
	ResultJson getList() {
		try {
			return ResultJson.trueState("", mgmtNews.getList());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("getLastList")
	ResultJson getLastList(int size) {
		try {
			return ResultJson.trueState("", mgmtNews.getLastList(size));
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}
}
