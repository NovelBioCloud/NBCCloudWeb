package com.novelbio.portal.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.mgmt.MgmtNews;
import com.novelbio.portal.biz.model.News;

@Controller
@RequestMapping("admin/news")
public class CtrlAdminNews extends AbstractController {

	@Autowired
	MgmtNews mgmtNews;

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
	@RequestMapping("create")
	ResultJson create(News news) {
		try {
			mgmtNews.create(news.getTitle(), news.getDescription(), news.getImage(), news.getLink(),
					news.getPublishDate());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState("");
	}

	@ResponseBody
	@RequestMapping("update")
	ResultJson update(News culture) {
		try {
			mgmtNews.update(culture.getId(), culture.getTitle(), culture.getDescription(), culture.getImage(),
					culture.getLink(), culture.getPublishDate());
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
			mgmtNews.remove(id);
			resp.setState(true);
		} catch (Exception e) {
			resp.setState(false);
		}
		return resp;
	}
}
