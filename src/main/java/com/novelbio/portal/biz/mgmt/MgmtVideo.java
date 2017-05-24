package com.novelbio.portal.biz.mgmt;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.novelbio.portal.biz.model.Video;
import com.novelbio.portal.biz.repo.RepoVideo;

@Service
public class MgmtVideo {

	@Autowired
	RepoVideo repoVideo;

	public void update(String id, String title, String description, String image, String link, Date publishDate) {
		Video video = repoVideo.findOne(id);
		video.setDescription(description);
		video.setImage(image);
		video.setLink(link);
		video.setPublishDate(publishDate);
		video.setTitle(title);
		repoVideo.save(video);
	}

	public List<Video> getList() {
		return Lists.newArrayList(repoVideo.findAll());
	}

	public Video get(String id) {
		return repoVideo.findOne(id);
	}

	public Video create(String title, String description, String image, String link, Date publishDate) {
		Video video = new Video();
		video.setDescription(description);
		video.setImage(image);
		video.setLink(link);
		video.setPublishDate(publishDate);
		video.setTitle(title);
		return repoVideo.save(video);
	}

	public void remove(String id) {
		repoVideo.delete(id);
	}

	public List<Video> getLastList(int size) {
		Sort sort = new Sort(Lists.newArrayList(new Sort.Order(Direction.DESC, "publishDate")));
		Pageable pageable = new PageRequest(size, size, sort);
		Page<Video> page = repoVideo.findAll(pageable);
		return page.getContent();
	}
}
