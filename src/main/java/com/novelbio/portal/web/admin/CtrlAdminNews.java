package com.novelbio.portal.web.admin;

import java.util.List;

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
			List<News> newsList = mgmtNews.getOrderList();
			return ResultJson.trueState("", newsList);
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("create")
	ResultJson create(News news) {
		try {
			mgmtNews.create(news.getTitle(), news.getImage(), news.getLink(), news.getPublishDate(),
					news.getDescription());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState("");
	}

	@ResponseBody
	@RequestMapping("update")
	ResultJson update(News culture) {
		try {
			mgmtNews.update(culture.getId(), culture.getTitle(), culture.getImage(), culture.getLink(),
					culture.getPublishDate(), culture.getDescription());
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
