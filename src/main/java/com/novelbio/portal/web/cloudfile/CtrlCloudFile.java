package com.novelbio.portal.web.cloudfile;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.biz.entity.CloudFileEntity;
import com.novelbio.portal.biz.entity.CloudFileView;
import com.novelbio.portal.biz.mgmt.MgmtCloudFile;
import com.novelbio.portal.biz.mgmt.MgmtCloudFileEntity;
import com.novelbio.portal.biz.mgmt.MgmtCloudFileView;
import com.novelbio.portal.biz.model.CloudFile;

@Controller
@RequestMapping(value = "/cloudFile")
public class CtrlCloudFile {
	@Autowired
	MgmtCloudFile mgmtCloudFile;
	@Autowired
	MgmtCloudFileEntity mgmtCloudFileEntity;
	@Autowired
	MgmtCloudFileView mgmtCloudFileView;

	@RequestMapping("getInputStream")
	void getInputStream(String id, HttpServletResponse response) {
		try {
			CloudFile file = mgmtCloudFile.get(id);
			CloudFileEntity entity = mgmtCloudFileEntity.create(file);
			mgmtCloudFileEntity.write(entity, response);
		} catch (Exception e) {
			try {
				response.sendError(500);
			} catch (IOException e1) {
			}
		}
	}

	@RequestMapping("getImageList")
	@ResponseBody
	List<CloudFileView> getImageList() {
		return mgmtCloudFileView.getImageList();
	}
}
