package com.novelbio.portal.web.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.mgmt.MgmtVideo;

@Controller
@RequestMapping(value = "/home/video")
public class CtrlHomeVideo extends AbstractController {
	@Autowired
	MgmtVideo mgmtNews;

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
