package com.novelbio.portal.base.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.core.io.ClassPathResource;

/**
 * 添加初始化的数据
 * 
 * @author renyx
 *
 */
public class InitParamListener implements ServletContextListener {

	public InitParamListener() {
	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		addContextPath(sc);
		addWebappProperties(sc);
	}

	private void addWebappProperties(ServletContext sc) {
		ClassPathResource resource = new ClassPathResource("webapp.properties");
		Properties p = new Properties();
		try (InputStream inStream = resource.getInputStream()) {
			p.load(inStream);
			p.entrySet().stream().forEach(it -> {
				sc.setAttribute((String) it.getKey(), (String) it.getValue());
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addContextPath(ServletContext sc) {
		String contextPath = sc.getContextPath();
		sc.setAttribute("contextPath", contextPath);
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

}
