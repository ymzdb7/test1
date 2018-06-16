package com.winhands.base.pay.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winhands.base.pay.dao.PayDao;
import com.winhands.base.pay.entity.PayHis;
import com.winhands.base.pay.service.PayService;
@Service
@Transactional(readOnly=false)
public class PayServiceImpl implements PayService {
	private final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);
	@Autowired
    private PayDao payDao;
	@Override
	public PayHis save(PayHis payHis) {
		return payDao.save(payHis);
	}
	

}
