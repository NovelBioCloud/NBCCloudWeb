package com.novelbio.portal.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.web.ResultJson;
import com.novelbio.portal.biz.application.domain.Culture;
import com.novelbio.portal.biz.application.domain.History;
import com.novelbio.portal.biz.application.domain.Recruitment;
import com.novelbio.portal.biz.home.mgmt.MgmtCulture;
import com.novelbio.portal.biz.home.mgmt.MgmtHistory;
import com.novelbio.portal.biz.home.mgmt.MgmtRecruitment;

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

	@ResponseBody
	@RequestMapping("getCulture")
	ResultJson getCulture() {
		try {
			return ResultJson.trueResp(mgmtCulture.getCulture());
		} catch (Exception e) {
			return ResultJson.falseResp(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("updateCulture")
	ResultJson updateCulture(Culture culture) {
		try {
			mgmtCulture.update(culture.getCoporationCulture(), culture.getEnvironment(), culture.getActivity());
		} catch (Exception e) {
			return ResultJson.falseResp(e.getMessage());
		}
		return ResultJson.trueResp();
	}

	@ResponseBody
	@RequestMapping("updateHistory")
	ResultJson updateHistory(History history) {
		try {
			mgmtHistory.update(history.getId(), history.getTime(), history.getContent());
		} catch (Exception e) {
			return ResultJson.falseResp(e.getMessage());
		}
		return ResultJson.trueResp();
	}

	@ResponseBody
	@RequestMapping("createHistory")
	ResultJson createHistory(History history) {
		try {
			mgmtHistory.create(history.getTime(), history.getContent());
		} catch (Exception e) {
			return ResultJson.falseResp(e.getMessage());
		}
		return ResultJson.trueResp();
	}

	@ResponseBody
	@RequestMapping("updateRecruitment")
	ResultJson updateRecruitment(Recruitment recruitment) {
		try {
			mgmtRecruitment.update(recruitment.getId(), recruitment.getName(), recruitment.getType(),
					recruitment.getNumber(), recruitment.getDescription(), recruitment.getPublishTime(),
					recruitment.getLink(), recruitment.getWorkLocation());
		} catch (Exception e) {
			return ResultJson.falseResp(e.getMessage());
		}
		return ResultJson.trueResp();
	}

	@ResponseBody
	@RequestMapping("createRecruitment")
	ResultJson createRecruitment(Recruitment recruitment) {
		try {
			mgmtRecruitment.create(recruitment.getName(), recruitment.getType(), recruitment.getNumber(),
					recruitment.getDescription(), recruitment.getPublishTime(), recruitment.getLink(),
					recruitment.getWorkLocation());
		} catch (Exception e) {
			return ResultJson.falseResp(e.getMessage());
		}
		return ResultJson.trueResp();
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

	@ResponseBody
	@RequestMapping("removeHistory")
	ResultJson removeHistory(String id) {
		ResultJson resp = new ResultJson();
		try {
			mgmtHistory.remove(id);
			resp.setState(true);
		} catch (Exception e) {
			resp.setState(false);
		}
		return resp;
	}

	@ResponseBody
	@RequestMapping("removeRecruitment")
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
