package com.novelbio.portal.biz.mgmt;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelbio.portal.biz.entity.CloudFileView;
import com.novelbio.portal.biz.model.CloudFile;

@Service
public class MgmtCloudFileView {
	@Autowired
	ConfigService configService;
	@Autowired
	MgmtCloudFile mgmtCloudFile;

	public CloudFileView get(String id) {
		CloudFile file = mgmtCloudFile.get(id);
		CloudFileView view = new CloudFileView(file);
		view.setInputStream("cloudFile/getInputStream?id=" + id);
		return view;
	}

	public CloudFileView get(CloudFile file) {
		CloudFileView view = new CloudFileView(file);
		view.setInputStream("cloudFile/getInputStream?id=" + file.getId());
		return view;
	}

	public List<CloudFileView> getImageList() {
		return mgmtCloudFile.list().stream().filter(it -> {
			FileNameMap fileNameMap = URLConnection.getFileNameMap();
			String type = fileNameMap.getContentTypeFor(it.getName());
			return StringUtils.startsWith(type, "image");
		}).map(it -> this.get(it)).collect(Collectors.toList());
	}

	public List<CloudFileView> getList() {
		return mgmtCloudFile.list().stream().map(it -> this.get(it)).collect(Collectors.toList());
	}
}
