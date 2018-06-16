package com.winhands.base.org.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winhands.base.org.entity.Org;
import com.winhands.base.org.repository.MBOrgDao;
import com.winhands.base.org.repository.OrgDao;
import com.winhands.base.org.service.OrgService;
import com.winhands.base.orm.util.DynamicSpecifications;
import com.winhands.base.orm.util.PageUtil;
import com.winhands.base.orm.util.SearchFilter;
@Transactional(readOnly=false)
@Service
public class OrgServiceImpl implements OrgService {
	private final Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class); 
	@Autowired
    private OrgDao orgDao;
	@Autowired
    private MBOrgDao mBOrgDao;
	@Override
	public Page<Org> findOrgList(int page, int rows, Map<String, Object> conditions, String sortColum) {
		logger.info("进入字典查询dao层!");
		PageRequest pageRequest = PageUtil.buildPageRequestDesc(page, rows, sortColum,"ASC");
	    Map<String, SearchFilter> filters = SearchFilter.parse(conditions); 
	    Specification<Org> spec = DynamicSpecifications.bySearchFilter(filters.values(), Org.class);
	    Page<Org> pages = orgDao.findAll(spec, pageRequest); 
		return pages; 
	}

	@Override
	public List<Org> findAll() {
		Sort sort = new Sort(Direction.ASC, "orderId");
		Iterable<Org> iterable = orgDao.findAll(sort);
		List<Org> list  = new ArrayList<Org>(); 
		Iterator<Org> iter = iterable.iterator(); 
		while(iter.hasNext()){
			list.add(iter.next());
		}
		return list;
	}

	@Override
	public Org save(Org org) {
		return orgDao.save(org);
	}

	@Override
	public Org findByOrgId(String id) {
		return orgDao.findOne(id);
	}

	@Override
	public void deleteByOrgId(String id) {
		 orgDao.delete(id);
	}

	@Override
	public List<Org> findOrgByPId(String id) {
		return orgDao.findOrgByParentOrgId(id);
	}


	@Override
	public List<Org> queryOrgListByType(Map<String, Object> map) {
		return mBOrgDao.queryOrgListByType(map);
	}

	@Override
	public List<Org> findOrgAreaList(Map<String, Object> conditions,
			String sortColum) {
		logger.info("进入字典查询dao层!");
	    Map<String, SearchFilter> filters = SearchFilter.parse(conditions); 
	    Specification<Org> spec = DynamicSpecifications.bySearchFilter(filters.values(), Org.class);
	    List<Org> orgList = orgDao.findAll(spec); 
		return orgList; 
	}

	@Override
	public List<Org> queryOrgListByPid(Map<String, Object> map) {
	 return	mBOrgDao.queryOrgListByPid(map);
	}

	@Override
	public List<Org> queryOrgListType2(Map<String, Object> map) {
		return mBOrgDao.queryOrgListType2(map);
	}

	@Override
	public Org findPidByOrgId(Map<String, Object> map) {
		return mBOrgDao.findPidByOrgId(map);
	}
  
}
