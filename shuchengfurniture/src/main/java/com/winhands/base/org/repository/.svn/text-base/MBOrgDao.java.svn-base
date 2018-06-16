package com.winhands.base.org.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winhands.base.org.entity.Org;
import com.winhands.base.service.MyBatisRepository;
 
@Component
public interface MBOrgDao extends MyBatisRepository{
	 public List<Org> queryOrgListByType(Map<String,Object> map);
	 public List<Org> queryOrgListByPid(Map<String,Object> map);
	 public List<Org> queryOrgListType2(Map<String,Object> map);
	 public Org findPidByOrgId(Map<String,Object> map );
	 
}
