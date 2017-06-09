package com.novelbio.portal.biz.mgmt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Path;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.novelbio.base.dataOperate.TxtReadandWrite;
import com.novelbio.base.fileOperate.FileOperate;
import com.novelbio.portal.base.MIMETypes;
import com.novelbio.portal.biz.entity.CloudFileEntity;
import com.novelbio.portal.biz.model.CloudFile;

@Service
public class MgmtCloudFileEntity {
	@Autowired
	ConfigService configService;

	public CloudFileEntity create(CloudFile file) {
		CloudFileEntity entity = new CloudFileEntity(file);
		entity.setConfigService(configService);
		return entity;
	}

	public void save(CloudFileEntity entity, InputStream is) {
		Path parentPath = entity.getStorePath().getParent();
		if (!FileOperate.isFileFolderExist(parentPath)) {
			FileOperate.createFolders(parentPath);
		}
		TxtReadandWrite txtRw = new TxtReadandWrite(entity.getStorePath(), true);
		txtRw.writefile(is);
		txtRw.close();
	}

	public void write(CloudFileEntity entity, HttpServletResponse response, boolean download) {
		String dispositionType = download ? "attachment" : "inline";

		Path path = entity.getStorePath();
		String fileName = "error";
		try {
			fileName = StringUtils.toEncodedString(entity.getName().getBytes("utf-8"), Charset.forName("ISO8859-1"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// FileNameMap fileNameMap = URLConnection.getFileNameMap();
		// String contentType = fileNameMap.getContentTypeFor(fileName);
		String contentType = MIMETypes.getContentTypeFor(fileName);
		response.setContentType(contentType);

		response.setHeader("Content-Disposition", dispositionType + ";fileName=" + fileName);

		try (InputStream is = FileOperate.getInputStream(path)) {
			OutputStream os = response.getOutputStream();
			StreamUtils.copy(is, os);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
