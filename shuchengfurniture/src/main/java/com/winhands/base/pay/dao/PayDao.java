package com.winhands.base.pay.dao;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.winhands.base.pay.entity.PayHis;
@Component
public interface PayDao  extends PagingAndSortingRepository<PayHis, String> ,JpaSpecificationExecutor<PayHis>{
}
