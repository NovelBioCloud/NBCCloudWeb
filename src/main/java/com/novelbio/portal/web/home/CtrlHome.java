package com.novelbio.portal.web.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.biz.mgmt.MgmtHistory;
import com.novelbio.portal.biz.mgmt.MgmtNews;
import com.novelbio.portal.biz.mgmt.MgmtRecruitment;

@Controller
@RequestMapping(value = "/home")
public class CtrlHome extends AbstractController {
	@Autowired
	MgmtNews mgmtNews;
	@Autowired
	MgmtHistory mgmtHistory;
	@Autowired
	MgmtRecruitment mgmtRecruitment;

	@RequestMapping(value = { "test" })
	public String test() {
		return "core-module/home/html/test";
	}

	@RequestMapping(value = { "" })
	public String defaultPage() {
		return "redirect:/home/index";
	}

	@RequestMapping(value = { "index" })
	public String index() {
		return "core-module/home/html/index";
	}

	@RequestMapping(value = "/productList1")
	public String productList1() {
		return "core-module/home/html/productList1";
	}

	@RequestMapping(value = "/productList2")
	public String productList2() {
		return "core-module/home/html/productList2";
	}

	@RequestMapping(value = "/productList3")
	public String productList3() {
		return "core-module/home/html/productList3";
	}

	@RequestMapping(value = "/productList4")
	public String productList4() {
		return "core-module/home/html/productList4";
	}

	@RequestMapping(value = "/product")
	public String product() {
		return "core-module/home/html/product";
	}

	@RequestMapping(value = "/news")
	public String news() {
		return "core-module/home/html/news";
	}

	@RequestMapping(value = "/newsInfo")
	public String newsInfo() {
		return "core-module/home/html/newsInfo";
	}

	@RequestMapping(value = "/about")
	public String about() {
		return "core-module/home/html/about";
	}

}
