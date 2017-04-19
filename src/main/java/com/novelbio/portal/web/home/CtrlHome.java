package com.novelbio.portal.web.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.web.ResultJson;
import com.novelbio.portal.biz.home.mgmt.MgmtHistory;
import com.novelbio.portal.biz.home.mgmt.MgmtRecruitment;

@Controller
@RequestMapping(value = "/home")
public class CtrlHome extends AbstractController {

	@Autowired
	MgmtHistory mgmtHistory;
	@Autowired
	MgmtRecruitment mgmtRecruitment;

	@RequestMapping(value = { "" })
	public String defaultPage() {
		return "redirect:home/index";
	}

	@RequestMapping(value = { "index" })
	public String index() {
		return "core-module/home/html/index";
	}

	@RequestMapping(value = "/product")
	public String product() {
		return "core-module/home/html/product";
	}

	@RequestMapping(value = "/news")
	public String news() {
		return "core-module/home/html/news";
	}

	@RequestMapping(value = "/about")
	public String about() {
		return "core-module/home/html/about";
	}

	@ResponseBody
	@RequestMapping("getListHistory")
	ResultJson getListHistory() {
		try {
			return ResultJson.trueResp(mgmtHistory.list());
		} catch (Exception e) {
			return ResultJson.falseResp(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("getListRecruitment")
	ResultJson getListRecruitment() {
		try {
			return ResultJson.trueResp(mgmtRecruitment.list());
		} catch (Exception e) {
			return ResultJson.falseResp(e.getMessage());
		}
	}
}
