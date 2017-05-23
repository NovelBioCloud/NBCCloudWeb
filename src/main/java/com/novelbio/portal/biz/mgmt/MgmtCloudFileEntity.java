package com.novelbio.portal.biz.mgmt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Path;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelbio.base.dataOperate.TxtReadandWrite;
import com.novelbio.base.fileOperate.FileOperate;
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

	public void write(CloudFileEntity entity, HttpServletResponse response) {
		Path path = entity.getStorePath();
		String fileName = entity.getName();

		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor(fileName);
		response.setContentType(type);
		if (!StringUtils.startsWith(type, "image")) {
			try {
				response.setHeader("Content-Disposition",
						"fileName=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		try (InputStream is = FileOperate.getInputStream(path)) {
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = is.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
