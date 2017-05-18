package com.novelbio.portal.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.mgmt.MgmtInformation;
import com.novelbio.portal.biz.model.Information;

@Controller
@RequestMapping("admin/information")
public class CtrlAdminInformation extends AbstractController {

	@Autowired
	MgmtInformation mgmtInformation;

	@ResponseBody
	@RequestMapping("getList")
	ResultJson getList() {
		try {
			return ResultJson.trueState(null, mgmtInformation.getList());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("update")
	ResultJson update(Information info) {
		try {
			mgmtInformation.update(info.getId(), info.getKey(), info.getName(), info.getValue());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState();
	}

	@ResponseBody
	@RequestMapping("remove")
	ResultJson remove(Information info) {
		try {
			mgmtInformation.update(info.getId(), info.getKey(), info.getName(), info.getValue());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState();
	}

	@ResponseBody
	@RequestMapping("create")
	ResultJson create(Information info) {
		try {
			mgmtInformation.create(info.getKey(), info.getName(), info.getValue());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState("");
	}
}
