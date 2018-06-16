package com.winhands.cshj.article.service.impl;
  

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winhands.cshj.article.entity.Directory;
import com.winhands.cshj.article.repository.DirectoryDao;
import com.winhands.cshj.article.repository.MBDirectoryDao;
import com.winhands.cshj.article.service.DirectoryService;

@Service
@Transactional(readOnly=false)
public class DirectoryServiceImpl implements DirectoryService {
	private final Logger logger = LoggerFactory.getLogger(DirectoryServiceImpl.class);
	@Autowired
    private DirectoryDao directoryDao;
	@Autowired
    private MBDirectoryDao mBDirectoryDao;
	@Override
	public List<Directory> findDirListByPId(Map<String, Object> conditions) {
		return mBDirectoryDao.findDirListByPId(conditions);
	}
	@Override
	public Directory save(Directory directory) {
		return directoryDao.save(directory);
	}
	@Override
	public Directory findByDirId(String dir_id) {
		return directoryDao.findOne(dir_id);
	}
	@Override
	public void delDirectoryByDirId(String dir_id) {
		mBDirectoryDao.delDirectoryByDirId(dir_id);
	}
	@Override
	public List<Directory> findDirListByPIdWeb(Map<String, Object> conditions) {
		return mBDirectoryDao.findDirListByPIdWeb(conditions);
	}
	 
	
}
