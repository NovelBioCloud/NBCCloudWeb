package com.novelbio.portal.biz.mgmt;

import java.util.Collections;
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

	public void update(String id, String title, String image, String doc, Date publishDate, String description) {

		News news = repoNews.findOne(id);
		news.setDescription(description);
		news.setImage(image);
		news.setLink(doc);
		news.setPublishDate(publishDate);
		news.setTitle(title);
		repoNews.save(news);
	}

	public List<News> getList() {
		return Lists.newArrayList(repoNews.findAll());
	}

	public List<News> getOrderList() {
		List<News> newsList = this.getList();
		Collections.sort(newsList, (o2, o1) -> {
			if (o1.getPublishDate() == null && o2.getPublishDate() == null) {
				return 0;
			} else if (o1.getPublishDate() != null && o2.getPublishDate() == null) {
				return 1;
			} else if (o1.getPublishDate() == null && o2.getPublishDate() != null) {
				return -1;
			} else {
				long time1 = o1.getPublishDate().getTime();
				long time2 = o2.getPublishDate().getTime();
				if (time1 > time2) {
					return 1;
				} else if (time1 == time2) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		return newsList;
	}

	public News getNews(String id) {
		return repoNews.findOne(id);
	}

	public News create(String title, String image, String doc, Date publishDate, String description) {
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

	public News get(String id) {
		return repoNews.findOne(id);
	}
}
