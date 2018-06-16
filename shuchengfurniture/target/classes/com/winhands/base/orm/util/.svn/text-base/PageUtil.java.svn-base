package com.winhands.base.orm.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageUtil {
	public static PageRequest buildPageRequestDesc(int pageNumber, int pagzSize, String sortColum,String direction) {
		if("desc".equals(direction)){
			 Sort sort = new Sort(Direction.DESC, sortColum);
			 return new PageRequest(pageNumber - 1, pagzSize, sort);
		}else{
			 Sort sort = new Sort(Direction.ASC, sortColum);
			 return new PageRequest(pageNumber - 1, pagzSize, sort);
		} 
    }
	
	public static PageRequest buildPageRequestDesc(int pageNumber, int pagzSize, String sortColum) {
        Sort sort = new Sort(Direction.DESC, sortColum);
        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }
}
