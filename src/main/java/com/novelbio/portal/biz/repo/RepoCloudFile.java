package com.novelbio.portal.biz.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.novelbio.portal.biz.model.CloudFile;

public interface RepoCloudFile extends PagingAndSortingRepository<CloudFile, String> {

}
