package com.winhands.cshj.article.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.winhands.cshj.article.entity.Directory;



public interface DirectoryDao extends PagingAndSortingRepository<Directory, String> ,JpaSpecificationExecutor<Directory>{ 

}
