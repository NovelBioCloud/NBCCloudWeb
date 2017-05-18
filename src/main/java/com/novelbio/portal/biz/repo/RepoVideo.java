package com.novelbio.portal.biz.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.novelbio.portal.biz.model.Video;

public interface RepoVideo extends PagingAndSortingRepository<Video, String> {

}
