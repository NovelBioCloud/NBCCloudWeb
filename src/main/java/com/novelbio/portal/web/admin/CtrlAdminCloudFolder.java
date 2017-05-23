package com.novelbio.portal.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.mgmt.MgmtCloudFolder;
import com.novelbio.portal.biz.model.CloudFolder;

@Controller
@RequestMapping("admin/cloudFolder")
public class CtrlAdminCloudFolder extends AbstractController {

	@Autowired
	MgmtCloudFolder mgmtCloudFolder;

	@ResponseBody
	@RequestMapping("get")
	ResultJson get(String id) {
		try {
			return ResultJson.trueState(null, mgmtCloudFolder.get(id));
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("update")
	ResultJson update(CloudFolder cloudFolder) {
		try {
			mgmtCloudFolder.update(cloudFolder.getId(), cloudFolder.getName(), cloudFolder.getPath(),
					cloudFolder.getDescription());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState();
	}

	@ResponseBody
	@RequestMapping("create")
	ResultJson createHistory(CloudFolder cloudFolder) {
		try {
			mgmtCloudFolder.create(cloudFolder.getName(), cloudFolder.getPath(), cloudFolder.getDescription());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState();
	}

	@ResponseBody
	@RequestMapping("getList")
	ResultJson getListHistory() {
		try {
			return ResultJson.trueState("", mgmtCloudFolder.list());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("remove")
	ResultJson removeHistory(String id) {
		ResultJson resp = new ResultJson();
		try {
			mgmtCloudFolder.remove(id);
			resp.setState(true);
		} catch (Exception e) {
			resp.setState(false);
		}
		return resp;
	}
}
