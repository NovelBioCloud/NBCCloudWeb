package com.novelbio.portal.base.framework;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 抽象控制器
 * 
 * @author renyx
 *
 */
public abstract class AbstractController {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, dateEditor); 
	}

}
