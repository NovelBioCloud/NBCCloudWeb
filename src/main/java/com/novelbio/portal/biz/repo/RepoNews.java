package com.novelbio.portal.biz.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.novelbio.portal.biz.model.News;

public interface RepoNews extends PagingAndSortingRepository<News, String> {

}
