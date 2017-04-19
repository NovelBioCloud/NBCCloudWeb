package com.novelbio.portal.web.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novelbio.portal.base.framework.AbstractController;

@Controller
@RequestMapping("")
public class CtrlApplication extends AbstractController {

	@RequestMapping("")
	String index() {
		return "redirect:home";
	}

}
