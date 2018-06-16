package com.winhands.cshj.fee.service.impl;

import com.winhands.cshj.fee.entity.MemberShipFee;
import com.winhands.cshj.fee.repository.FeeDao;
import com.winhands.cshj.fee.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author guojun
 */
@Service
@Transactional
public class FeeServiceImpl implements FeeService {
    @Autowired
    private FeeDao dao;
    @Override
    public List<MemberShipFee> queryFeeList(MemberShipFee msf) throws Exception {
        return dao.queryFeeList(msf);
    }

    @Override
    public int saveFee(MemberShipFee msf) throws Exception {
        return dao.saveFee(msf);
    }

    @Override
    public int deleteFee(String id) throws Exception {
        return dao.deleteFeeById(id);
    }

    @Override
    public List<MemberShipFee> queryInUseFeeList(MemberShipFee msf) throws Exception {
        return dao.queryInUseFeeList(msf);
    }
    @Override
    public String queryOrderNum() throws Exception{
    	 return dao.queryOrderNum();
    }

	@Override
	public MemberShipFee queryFeeById(MemberShipFee msf) throws Exception {
		return dao.queryFeeById(msf);
	}
}
