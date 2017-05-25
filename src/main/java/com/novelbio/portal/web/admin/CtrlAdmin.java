package com.novelbio.portal.web.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.entity.UserEntity;
import com.novelbio.portal.biz.mgmt.MgmtCulture;
import com.novelbio.portal.biz.mgmt.MgmtHistory;
import com.novelbio.portal.biz.mgmt.MgmtRecruitment;
import com.novelbio.portal.biz.model.User;

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

	@RequestMapping("isLogin")
	@ResponseBody
	ResultJson isLogin(HttpSession session) {
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		boolean flag = false;
		if (userEntity != null && userEntity.isLogin()) {
			flag = true;
		}
		return new ResultJson(flag, "");
	}

	@RequestMapping("login")
	@ResponseBody
	ResultJson login(String username, String password, HttpSession session) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		UserEntity userEntity = new UserEntity(user);
		boolean flag = userEntity.isLogin();
		if (flag) {
			session.setAttribute("user", userEntity);
		} else {
			session.removeAttribute("user");
		}
		return new ResultJson(flag, "");
	}
}
