package com.winhands.ncjc.push.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winhands.ncjc.push.entity.PushMsg;
import com.winhands.ncjc.push.entity.PushUserDevice;
import com.winhands.ncjc.push.repository.MBPushDao;
import com.winhands.ncjc.push.repository.PushDao;
import com.winhands.ncjc.push.repository.PushMsgDao;
import com.winhands.ncjc.push.service.PushApiService;


@Service
@Transactional(readOnly=false)
public class PushServiceImpl implements PushApiService {
	private static final  Logger logger = LoggerFactory.getLogger(PushServiceImpl.class);
	@Autowired
    private PushDao pushDao;
	@Autowired
	private MBPushDao mBPushDao;
	@Autowired
	private PushMsgDao pushMsgDao;
	@Override
	public List<PushUserDevice> findAllUser(PushUserDevice pushUserDevice) throws Exception {
		Sort sort = new Sort(Direction.ASC, "orderId");
		Iterable<PushUserDevice> iterable = pushDao.findAll(sort);
		List<PushUserDevice> list  = new ArrayList<PushUserDevice>(); 
		Iterator<PushUserDevice> iter = iterable.iterator(); 
		while(iter.hasNext()){
			list.add(iter.next());
		}
		return list;
	}

	@Override
	public boolean deleteUser2Push(PushUserDevice pushUserDevice)
			throws Exception {
		return false;
	}


	@Override
	public PushUserDevice saveUser2Push(PushUserDevice pushUserDevice)
			throws Exception {
		return pushDao.save(pushUserDevice);
	}

	@Override
	public List<Object> findPushUserByUserID(PushMsg pushMsg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PushUserDevice findByPushUserDevice(Map<String, Object> map) {
		return mBPushDao.findByPushUserDevice(map);
	}

	@Override
	public PushMsg savePushMsg(PushMsg pushMsg) throws Exception {
		return pushMsgDao.save(pushMsg);
	}

	@Override
	public List<PushMsg> queryUserMsg(Map<String, Object> map) throws Exception {
		return mBPushDao.queryUserMsg(map);
	} 
	

}
