package com.novelbio.portal.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.mgmt.MgmtCulture;
import com.novelbio.portal.biz.mgmt.MgmtHistory;
import com.novelbio.portal.biz.mgmt.MgmtRecruitment;
import com.novelbio.portal.biz.model.Recruitment;

@Controller
@RequestMapping("admin/recruitment")
public class CtrlAdminRecruitment extends AbstractController {

	@Autowired
	MgmtHistory mgmtHistory;
	@Autowired
	MgmtRecruitment mgmtRecruitment;
	@Autowired
	MgmtCulture mgmtCulture;

	@RequestMapping
	String index() {
		return "biz-module/home/html/admin";
	}

	@ResponseBody
	@RequestMapping("update")
	ResultJson updateRecruitment(Recruitment recruitment) {
		try {
			mgmtRecruitment.update(recruitment.getId(), recruitment.getName(), recruitment.getType(),
					recruitment.getNumber(), recruitment.getDescription(), recruitment.getPublishTime(),
					recruitment.getLink(), recruitment.getWorkLocation());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState();
	}

	@ResponseBody
	@RequestMapping("create")
	ResultJson createRecruitment(Recruitment recruitment) {
		try {
			mgmtRecruitment.create(recruitment.getName(), recruitment.getType(), recruitment.getNumber(),
					recruitment.getDescription(), recruitment.getPublishTime(), recruitment.getLink(),
					recruitment.getWorkLocation());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState();
	}

	@ResponseBody
	@RequestMapping("getList")
	ResultJson getListRecruitment() {
		try {
			return ResultJson.trueState("", mgmtRecruitment.getList());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("remove")
	ResultJson removeRecruitment(String id) {
		ResultJson resp = new ResultJson();
		try {
			mgmtRecruitment.remove(id);
			resp.setState(true);
		} catch (Exception e) {
			resp.setState(false);
		}
		return resp;
	}
}
