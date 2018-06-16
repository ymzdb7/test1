package com.winhands.cshj.emp.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.winhands.cshj.emp.entity.Emp;

public interface EmpDao extends PagingAndSortingRepository<Emp, String> ,JpaSpecificationExecutor<Emp>{ 

}
