package com.novelbio.portal.biz.entity;

import com.novelbio.portal.biz.model.CloudFile;

/**
 * 前台使用的视图对象
 * 
 * @author novelbio
 *
 */
public class CloudFileView extends CloudFile {
	/**
	 * 获取文件对应的流数据
	 */
	String inputStream;

	public CloudFileView(CloudFile file) {
		this.setDescription(file.getDescription());
		this.setId(file.getId());
		this.setLink(file.getLink());
		this.setName(file.getName());
		this.setPath(file.getPath());
	}

	public String getInputStream() {
		return inputStream;
	}

	public void setInputStream(String inputStream) {
		this.inputStream = inputStream;
	}

}
