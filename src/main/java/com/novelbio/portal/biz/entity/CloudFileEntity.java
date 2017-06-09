package com.novelbio.portal.biz.entity;

import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.novelbio.base.fileOperate.FileOperate;
import com.novelbio.portal.biz.mgmt.ConfigService;
import com.novelbio.portal.biz.model.CloudFile;

/**
 * 文件操作的实体对象
 * 
 * @author renyaoxiang
 *
 */
public class CloudFileEntity extends CloudFile {
	private ConfigService configService;

	public CloudFileEntity() {

	}

	public CloudFileEntity(CloudFile cloudFile) {
		this.setId(cloudFile.getId());
		this.setName(cloudFile.getName());
		this.setPath(cloudFile.getPath());
		this.setDescription(cloudFile.getDescription());
		this.setLink(cloudFile.getLink());
	}

	public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	/**
	 * 获取实际的存储路径
	 * 
	 * @return
	 */
	public Path getStorePath() {
		Path path = null;
		if (StringUtils.isBlank(this.getPath())) {
			path = FileOperate.getPath(configService.getRootpath(), this.getLink());
		} else {
			path = FileOperate.getPath(configService.getRootpath(), this.getPath(), this.getLink());
		}
		return path;
	}

}
