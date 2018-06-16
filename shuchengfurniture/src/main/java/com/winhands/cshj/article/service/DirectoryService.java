package com.winhands.cshj.article.service;

import java.util.List;
import java.util.Map;

import com.winhands.cshj.article.entity.Directory;



public interface DirectoryService {
	//根据父节点id查询子列表
	public List<Directory> findDirListByPId(Map<String,Object> conditions);
	public List<Directory> findDirListByPIdWeb(Map<String,Object> conditions);
	//保存目录
	public Directory save(Directory directory);
	//根据id获取目录对象
	public Directory findByDirId(String dir_id); 
	public void delDirectoryByDirId(String dir_id); 
	
	
}
