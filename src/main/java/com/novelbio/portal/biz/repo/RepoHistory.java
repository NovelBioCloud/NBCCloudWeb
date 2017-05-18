package com.novelbio.portal.biz.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.novelbio.portal.biz.model.History;

public interface RepoHistory extends PagingAndSortingRepository<History, String> {

}
