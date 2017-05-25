package com.novelbio.portal.web.admin;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.novelbio.portal.base.framework.AbstractController;
import com.novelbio.portal.base.framework.ResultJson;
import com.novelbio.portal.biz.entity.CloudFileEntity;
import com.novelbio.portal.biz.mgmt.ConfigService;
import com.novelbio.portal.biz.mgmt.MgmtCloudFile;
import com.novelbio.portal.biz.mgmt.MgmtCloudFileEntity;
import com.novelbio.portal.biz.model.CloudFile;

@Controller
@RequestMapping("admin/cloudFile")
public class CtrlAdminCloudFile extends AbstractController {

	@Autowired
	MgmtCloudFile mgmtCloudFile;
	@Autowired
	ConfigService configService;
	@Autowired
	MgmtCloudFileEntity mgmtCloudFileEntity;

	@ResponseBody
	@RequestMapping("get")
	ResultJson get(String id) {
		try {
			return ResultJson.trueState(null, mgmtCloudFile.get(id));
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("update")
	ResultJson update(CloudFile cloudFile) {
		try {
			mgmtCloudFile.update(cloudFile.getId(), cloudFile.getName(), cloudFile.getPath(), cloudFile.getLink(),
					cloudFile.getDescription());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState();
	}

	@ResponseBody
	@RequestMapping("create")
	ResultJson createCloudFile(MultipartFile uploadFile, String path, String description) {
		try (InputStream is = uploadFile.getInputStream()) {
			String fileName = uploadFile.getOriginalFilename();
			String link = UUID.randomUUID().toString() + "-" + fileName;
			CloudFile toStoreFile = new CloudFile();
			toStoreFile.setName(fileName);
			toStoreFile.setLink(link);
			toStoreFile.setDescription(description);
			toStoreFile.setPath(path);
			CloudFileEntity entity = mgmtCloudFileEntity.create(toStoreFile);
			mgmtCloudFileEntity.save(entity, is);
			mgmtCloudFile.create(entity.getName(), entity.getPath(), entity.getLink(), entity.getDescription());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
		return ResultJson.trueState();
	}

	@ResponseBody
	@RequestMapping("getList")
	ResultJson getListCloudFile() {
		try {
			return ResultJson.trueState("", mgmtCloudFile.list());
		} catch (Exception e) {
			return ResultJson.falseState(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("remove")
	ResultJson removeCloudFile(String id) {
		ResultJson resp = new ResultJson();
		try {
			mgmtCloudFile.remove(id);
			resp.setState(true);
		} catch (Exception e) {
			resp.setState(false);
		}
		return resp;
	}
}
