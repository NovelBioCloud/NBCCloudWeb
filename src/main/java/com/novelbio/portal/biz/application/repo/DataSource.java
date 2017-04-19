package com.novelbio.portal.biz.application.repo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.novelbio.portal.biz.application.domain.Culture;
import com.novelbio.portal.biz.application.domain.DB;
import com.novelbio.portal.biz.application.domain.History;
import com.novelbio.portal.biz.application.domain.Recruitment;

@Service
public class DataSource {
	private DB application;
	private Logger log = LoggerFactory.getLogger(DataSource.class);
	private String USER_HOME = System.getProperty("user.home");

	public void save() {
		Path path = Paths.get(USER_HOME, "portalData", "data.db");
		try {
			String applicationJson = JSON.toJSONString(application, true);
			FileUtils.write(path.toFile(), applicationJson, "UTF-8");
		} catch (IOException e) {
			log.error("保存文件错误", e);
		}
	}

	public DB load() {
		Path path = Paths.get(USER_HOME, "portalData", "data.db");
		if (!Files.exists(path)) {
			try {
				if (!Files.exists(path.getParent())) {
					Files.createDirectories(path.getParent());
				}
				Files.createFile(path);
			} catch (IOException e) {
				log.error("创建数据库文件失败", e);
				throw new RuntimeException("创建数据库文件失败", e);
			}
		}
		File file = path.toFile();
		try {
			String applicationJson = FileUtils.readFileToString(file, "UTF-8");
			if (StringUtils.isBlank(applicationJson)) {
				application = new DB();
			} else {
				application = JSON.parseObject(applicationJson, DB.class);
			}
		} catch (IOException e) {
			application = new DB();
			log.error("保存文件错误", e);
		}
		return application;
	}

	synchronized public List<History> listHistory() {
		return this.application.clone().getListHistory();
	}

	synchronized public History getHistory(String id) {
		return this.application.getHistory(id).clone();
	}

	synchronized public Recruitment getRecruitment(String id) {
		return this.application.getRecruitment(id).clone();
	}

	synchronized public List<Recruitment> listRecruitment() {
		return this.application.clone().getListRecruitment();
	}

	synchronized public Culture getCulture() {
		return this.application.getCulture().clone();
	}

	synchronized public DB getApplication() {
		return this.application.clone();
	}

	@PostConstruct
	void init() {
		this.application = load();
	}

	synchronized public void doTransaction(Consumer<DB> callback) {
		callback.accept(application);
		this.save();
	}

}
