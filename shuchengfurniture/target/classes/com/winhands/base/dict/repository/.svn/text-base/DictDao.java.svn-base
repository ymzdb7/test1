package com.winhands.base.dict.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.winhands.base.dict.entity.Dict;

public interface DictDao extends PagingAndSortingRepository<Dict, String> ,JpaSpecificationExecutor<Dict>{ 

     public Dict findDictByDictIdAndSuperDictId(String dictId,String superDictId);
     public List<Dict> findDictListBySuperDictId(String superDictId);
     public Dict findDictByDictId(String dictId);
     
     
}
