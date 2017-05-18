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
import com.novelbio.portal.biz.model.History;

@Controller
@RequestMapping("admin/history")
public class CtrlAdminHistory extends AbstractController {

	@Autowired
	MgmtHistory mgmtHistory;
	@Autowired
	MgmtRecruitment mgmtRecruitment;
	@Autowired
	MgmtCulture mgmtCulture;

	@ResponseBody
	@RequestMapping("update")
	ResultJson updateHistory(History history) {
		try {
			mgmtHistory.update(history.getId(), history.getTime(), history.getContent());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState();
	}

	@ResponseBody
	@RequestMapping("create")
	ResultJson createHistory(History history) {
		try {
			mgmtHistory.create(history.getTime(), history.getContent());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState();
	}

	@ResponseBody
	@RequestMapping("getList")
	ResultJson getListHistory() {
		try {
			return ResultJson.trueState("", mgmtHistory.list());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("remove")
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

}
