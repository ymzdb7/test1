package com.winhands.cshj.emp.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.winhands.cshj.emp.entity.Emp;

public interface EmpService {
	
	/**保存接口**/
	public Emp save(Emp emp);
	/**按条件查询**/
	public List<Emp> findByConditions(Map<String, Object> conditions);
	
	/**查询注册用户清单**/
	public Page<Emp>  findEmpList(int page,int rows,Map<String, Object> conditions,String sortColum);
	
//	public List<Empl> findAll(Empl chef);
	 
//	public int delChefByChefId(String chef_id);
//	public void delChefById(String chef_id); 
//	 
//	//厨师档案汇总列表
//	public List<Empl>queryChefRecordList(Map<String, Object> conditions);
}
