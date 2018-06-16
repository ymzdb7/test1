package com.winhands.base.dict.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.winhands.base.dict.entity.Dict;
import com.winhands.base.dict.repository.DictDao;
import com.winhands.base.dict.service.DictService;
import com.winhands.base.orm.util.DynamicSpecifications;
import com.winhands.base.orm.util.PageUtil;
import com.winhands.base.orm.util.SearchFilter;

@Service
@Transactional(readOnly=false)
public class DictServiceImpl implements DictService {
	private final Logger logger = LoggerFactory.getLogger(DictServiceImpl.class);
	@Autowired
    private DictDao dictDao;
	
	public Page<Dict>  findDictList(int page,int rows,Map<String, Object> conditions,String sortColum){
	  logger.info("进入字典查询页!");
	  PageRequest pageRequest = PageUtil.buildPageRequestDesc(page, rows, sortColum);
      Map<String, SearchFilter> filters = SearchFilter.parse(conditions); 
      Specification<Dict> spec = DynamicSpecifications.bySearchFilter(filters.values(), Dict.class);
      Page<Dict> pages = dictDao.findAll(spec, pageRequest); 
	  return pages;
	}

	@Override
	public List<Dict> findAll(Dict dict) {
		Sort sort = new Sort(Direction.ASC, "orderId");
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("EQ_superDictId", dict.getSuperDictId());
		Map<String, SearchFilter> filters = SearchFilter.parse(hashMap); 
	    Specification<Dict> spec = DynamicSpecifications.bySearchFilter(filters.values(), Dict.class);
		Iterable<Dict> iterable = dictDao.findAll(spec, sort);
		List<Dict> list  = new ArrayList<Dict>(); 
		Iterator<Dict> iter = iterable.iterator(); 
		while(iter.hasNext()){
			list.add(iter.next());
		}
		return list;
	}

	@Override
	public Dict findDictByDictIdAndSuperDictId(String dictId,String superDictId) { 
		return dictDao.findDictByDictIdAndSuperDictId(dictId,superDictId);
	}

	@Override
	public Dict save(Dict dict) {
		// TODO Auto-generated method stub
		return dictDao.save(dict);
	}

	@Override
	public Dict findById(String id) {
		 
		return dictDao.findOne(id);
	}

	@Override
	public List<Dict> findDictListBySuperDictId(String superDictId) {
		return dictDao.findDictListBySuperDictId(superDictId);
	}

	@Override
	public Dict findDictByDictId(String dictId) {
		return dictDao.findDictByDictId(dictId);
	}
	
	
	
}
