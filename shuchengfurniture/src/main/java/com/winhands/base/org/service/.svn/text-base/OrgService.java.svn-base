package com.winhands.base.org.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.winhands.base.org.entity.Org;

public interface OrgService {
	/**
	 * 分页查询字典
	 * @param page
	 * @param rows
	 * @param conditions
	 * @param sortColum
	 * @return
	 */
	public Page<Org>  findOrgList(int page,int rows,Map<String, Object> conditions,String sortColum);
	public List<Org>  findOrgAreaList(Map<String, Object> conditions,String sortColum);
	public List<Org> findAll(); 
	
	public Org save(Org dict);
	
	public Org findByOrgId(String id);
	public Org findPidByOrgId(Map<String,Object> map );
	
	public void deleteByOrgId(String id);
	
	
	public List<Org> findOrgByPId(String id);
	public List<Org> queryOrgListByType(Map<String,Object> map);
	//查询父节点以及下面的子节点
	public List<Org> queryOrgListByPid(Map<String,Object> map);
	public List<Org> queryOrgListType2(Map<String,Object> map);
}
