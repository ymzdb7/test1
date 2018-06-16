package com.winhands.base.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.winhands.base.org.entity.Org;

public interface OrgDao  extends PagingAndSortingRepository<Org, String> ,JpaSpecificationExecutor<Org>{
    public List<Org> findOrgByParentOrgId(String id);
}
