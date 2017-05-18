package com.novelbio.portal.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.mgmt.MgmtCulture;
import com.novelbio.portal.biz.model.Culture;

@Controller
@RequestMapping("admin/culture")
public class CtrlAdminCulture extends AbstractController {

	@Autowired
	MgmtCulture mgmtCulture;

	@ResponseBody
	@RequestMapping("get")
	ResultJson get() {
		try {
			return ResultJson.trueState(null, mgmtCulture.getCulture());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("update")
	ResultJson update(Culture culture) {
		try {
			mgmtCulture.update(culture.getId(), culture.getCoporationCulture(), culture.getEnvironment(),
					culture.getActivity());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState();
	}
}
