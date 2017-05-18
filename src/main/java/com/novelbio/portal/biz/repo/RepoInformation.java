package com.novelbio.portal.biz.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.novelbio.portal.biz.model.Information;

public interface RepoInformation extends PagingAndSortingRepository<Information, String> {
	public Information findOneByKey(String key);
}
