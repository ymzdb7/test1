package com.winhands.cshj.article.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.winhands.cshj.article.entity.ArticleReadCollect;


public interface ArticleReadCollectDao extends PagingAndSortingRepository<ArticleReadCollect, String> ,JpaSpecificationExecutor<ArticleReadCollect>{ 

}
