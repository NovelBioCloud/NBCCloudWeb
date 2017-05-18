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
import com.novelbio.portal.biz.model.News;
import com.novelbio.portal.biz.repo.RepoNews;

@Service
public class MgmtNews {

	@Autowired
	RepoNews repoNews;

	public void update(String id, String title, String description, String image, String doc, Date publishDate) {

		News news = repoNews.findOne(id);
		news.setDescription(description);
		news.setImage(doc);
		news.setLink(image);
		news.setPublishDate(publishDate);
		news.setTitle(title);
		repoNews.save(news);
	}

	public List<News> getList() {
		return Lists.newArrayList(repoNews.findAll());
	}

	public News getNews(String id) {
		return repoNews.findOne(id);
	}

	public News create(String title, String description, String image, String doc, Date publishDate) {
		News news = new News();
		news.setDescription(description);
		news.setImage(doc);
		news.setLink(image);
		news.setPublishDate(publishDate);
		news.setTitle(title);
		return repoNews.save(news);
	}

	public void remove(String id) {
		repoNews.delete(id);
	}

	public Page<News> getPage(Pageable pageable) {
		Page<News> page = repoNews.findAll(pageable);
		return page;
	}

	public List<News> getLastList(int size) {
		Sort sort = new Sort(Lists.newArrayList(new Sort.Order(Direction.DESC, "publishDate")));
		Pageable pageable = new PageRequest(0, size, sort);
		Page<News> page = repoNews.findAll(pageable);
		return page.getContent();
	}
}
