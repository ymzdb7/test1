package com.winhands.base.dict.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.winhands.base.dict.entity.Dict;

public interface DictService {
	/**
	 * 分页查询字典
	 * @param page
	 * @param rows
	 * @param conditions
	 * @param sortColum
	 * @return
	 */
	public Page<Dict>  findDictList(int page,int rows,Map<String, Object> conditions,String sortColum);
	
	
	public List<Dict> findAll(Dict dict);
	
	public Dict findDictByDictIdAndSuperDictId(String dictId,String superDictId);
	
	public Dict save(Dict dict);
	
	public Dict findById(String id);
	public List<Dict> findDictListBySuperDictId(String superDictId);
	public Dict findDictByDictId(String dictId);
}
