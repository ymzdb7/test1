package com.winhands.cshj.fee.repository;

import com.winhands.base.service.MyBatisRepository;
import com.winhands.cshj.fee.entity.MemberShipFee;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author guojun
 */
@Component
public interface FeeDao extends MyBatisRepository {
    List<MemberShipFee> queryFeeList(MemberShipFee msf) throws Exception;
    int saveFee(MemberShipFee msf) throws Exception;
    int deleteFeeById(String id) throws Exception;
    List<MemberShipFee> queryInUseFeeList(MemberShipFee msf) throws Exception;
    public String queryOrderNum() throws Exception;
    MemberShipFee queryFeeById(MemberShipFee msf)throws Exception;
}
