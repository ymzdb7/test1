package com.winhands.cshj.emp.service.impl;
  
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winhands.base.orm.util.DynamicSpecifications;
import com.winhands.base.orm.util.PageUtil;
import com.winhands.base.orm.util.SearchFilter;
import com.winhands.cshj.emp.entity.Emp;
import com.winhands.cshj.emp.repository.EmpDao;
import com.winhands.cshj.emp.service.EmpService;
@Service
@Transactional(readOnly=false)
public class EmpServiceImpl implements EmpService {
	private final Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);
	@Autowired
    private EmpDao empDao; 
	
	
	@Override
	public Emp save(Emp emp) {
		// TODO Auto-generated method stub
		logger.info("注册:{},{}",emp.getIdcard(),emp.getPhone());
		return empDao.save(emp);
	}

	@Override
	public List<Emp> findByConditions(Map<String, Object> conditions) {
		
	    Map<String, SearchFilter> filters = SearchFilter.parse(conditions); 
	    Specification<Emp> spec = DynamicSpecifications.bySearchFilter(filters.values(), Emp.class);
	    return  empDao.findAll(spec); 
	}

	@Override
	public Page<Emp> findEmpList(int page, int rows, Map<String, Object> conditions, String sortColum) { 
		  PageRequest pageRequest = PageUtil.buildPageRequestDesc(page, rows, sortColum);
	      Map<String, SearchFilter> filters = SearchFilter.parse(conditions); 
	      Specification<Emp> spec = DynamicSpecifications.bySearchFilter(filters.values(), Emp.class);
	      Page<Emp> pages = empDao.findAll(spec, pageRequest);  
		  return pages;
	}

//	@Override
//	public Page<Empl> findChefList(int page, int rows,
//			Map<String, Object> conditions, String sortColum) {
//		PageRequest pageRequest = PageUtil.buildPageRequestDesc(page, rows, sortColum);
//	      Map<String, SearchFilter> filters = SearchFilter.parse(conditions); 
//	      Specification<Empl> spec = DynamicSpecifications.bySearchFilter(filters.values(), Empl.class);
//	      Page<Empl> pages = emplDao.findAll(spec, pageRequest);  
//		  return pages;
//	}
//	@Override
//	public List<Empl> findAll(Empl chef) {
//		Sort sort = new Sort(Direction.ASC, "orderId");
//		Iterable<Empl> iterable = emplDao.findAll(sort);
//		List<Empl> list  = new ArrayList<Empl>(); 
//		Iterator<Empl> iter = iterable.iterator(); 
//		while(iter.hasNext()){
//			list.add(iter.next());
//		}
//		return list;
//	}
//	@Override
//	public Empl save(Empl chef) {
//		return emplDao.save(chef);
//	}
//	@Override
//	public Emp findById(String id) {
//		return emplDao.findOne(id);
//	}
//	 
//	@Override
//	public void delChefById(String chef_id) {
//		emplDao.delete(chef_id);
//	}
//	@Override
//	public int delChefByChefId(String chef_id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public List<Emp> queryChefRecordList(Map<String, Object> conditions) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	 
	
}
