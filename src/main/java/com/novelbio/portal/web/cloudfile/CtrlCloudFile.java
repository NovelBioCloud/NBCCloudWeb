package com.novelbio.portal.web.cloudfile;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.entity.CloudFileEntity;
import com.novelbio.portal.biz.mgmt.MgmtCloudFile;
import com.novelbio.portal.biz.mgmt.MgmtCloudFileEntity;
import com.novelbio.portal.biz.mgmt.MgmtCloudFileView;
import com.novelbio.portal.biz.mgmt.MgmtVideo;
import com.novelbio.portal.biz.model.CloudFile;
import com.novelbio.portal.biz.model.Video;

@Controller
@RequestMapping(value = "/cloudFile")
public class CtrlCloudFile {
	@Autowired
	MgmtCloudFile mgmtCloudFile;
	@Autowired
	MgmtCloudFileEntity mgmtCloudFileEntity;
	@Autowired
	MgmtCloudFileView mgmtCloudFileView;
	@Autowired
	MgmtVideo mgmtVideo;

	@RequestMapping("getInputStream")
	void getInputStream(String id, HttpServletResponse response) {
		try {
			CloudFile file = mgmtCloudFile.get(id);
			CloudFileEntity entity = mgmtCloudFileEntity.create(file);
			mgmtCloudFileEntity.write(entity, response, false);
		} catch (Exception e) {
			try {
				response.sendError(500);
			} catch (IOException e1) {
			}
		}
	}

	@RequestMapping("getFileStream")
	void getFileStream(String id, HttpServletResponse response) {
		try {
			CloudFile file = mgmtCloudFile.get(id);
			CloudFileEntity entity = mgmtCloudFileEntity.create(file);
			mgmtCloudFileEntity.write(entity, response, false);
		} catch (Exception e) {
			try {
				response.sendError(500);
			} catch (IOException e1) {
			}
		}
	}

	@RequestMapping("download")
	void download(String id, HttpServletResponse response) {
		try {
			CloudFile file = mgmtCloudFile.get(id);
			CloudFileEntity entity = mgmtCloudFileEntity.create(file);
			mgmtCloudFileEntity.write(entity, response, true);
		} catch (Exception e) {
			try {
				response.sendError(500);
			} catch (IOException e1) {
			}
		}
	}

	@RequestMapping("getVideo")
	@ResponseBody
	ResultJson getVideo(String id) {
		CloudFile file = mgmtCloudFile.get(id);
		return new ResultJson(file != null, "", file);
	}

	@RequestMapping("getVideoList")
	@ResponseBody
	ResultJson getVideoList() {
		return ResultJson.trueState(null, mgmtCloudFileView.getVideoList());
	}

	@RequestMapping("getImageList")
	@ResponseBody
	ResultJson getImageList() {
		return ResultJson.trueState(null, mgmtCloudFileView.getImageList());
	}
}
