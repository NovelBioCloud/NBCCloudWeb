package com.novelbio.portal.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.mgmt.MgmtVideo;
import com.novelbio.portal.biz.model.Video;

@Controller
@RequestMapping("admin/video")
public class CtrlAdminVideo extends AbstractController {

	@Autowired
	MgmtVideo mgmtVideo;

	@ResponseBody
	@RequestMapping("getList")
	ResultJson getList() {
		try {
			return ResultJson.trueState("", mgmtVideo.getList());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("create")
	ResultJson create(Video video) {
		try {
			mgmtVideo.create(video.getTitle(), video.getDescription(), video.getImage(), video.getLink(),
					video.getPublishDate());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState("");
	}

	@ResponseBody
	@RequestMapping("update")
	ResultJson update(Video video) {
		try {
			mgmtVideo.update(video.getId(), video.getTitle(), video.getDescription(), video.getImage(), video.getLink(),
					video.getPublishDate());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState("");
	}

	@ResponseBody
	@RequestMapping("remove")
	ResultJson remove(String id) {
		ResultJson resp = new ResultJson();
		try {
			mgmtVideo.remove(id);
			resp.setState(true);
		} catch (Exception e) {
			resp.setState(false);
		}
		return resp;
	}
}
