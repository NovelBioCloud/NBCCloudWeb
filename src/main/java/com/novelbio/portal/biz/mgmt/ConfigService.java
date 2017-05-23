package com.novelbio.portal.biz.mgmt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * 配置文件服务
 * 
 * @author renyaoxiang
 * @date 2016年11月3日
 * 
 */
@Repository
public class ConfigService {

	/**
	 * 文档的根目录
	 */
	@Value("${rootPath}")
	private String rootpath;

	public String getRootpath() {
		return rootpath;
	}

}
