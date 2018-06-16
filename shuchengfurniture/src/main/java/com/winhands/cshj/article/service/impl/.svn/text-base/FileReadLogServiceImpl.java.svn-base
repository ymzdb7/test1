package com.winhands.cshj.article.service.impl;
  

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winhands.cshj.article.entity.FileReadLog;
import com.winhands.cshj.article.repository.FileReadLogDao;
import com.winhands.cshj.article.repository.MBFileReadLogDao;
import com.winhands.cshj.article.service.FileReadLogService;


@Service
@Transactional(readOnly=false)
public class FileReadLogServiceImpl implements FileReadLogService {
	private final Logger logger = LoggerFactory.getLogger(FileReadLogServiceImpl.class);
	@Autowired
    private FileReadLogDao fileReadLogDao;
	@Autowired
    private MBFileReadLogDao mBFileReadLogDao;
	@Override
	public FileReadLog save(FileReadLog fileReadLog) {
		return fileReadLogDao.save(fileReadLog);
	}
	@Override
	public List<FileReadLog> queryFileReadLogLimit(Map<String, Object> conditions) {
		return mBFileReadLogDao.queryFileReadLogLimit(conditions);
	}
	@Override
	public List<FileReadLog> queryLogList(Map<String, Object> conditions) {
		return mBFileReadLogDao.queryLogList(conditions);
	} 
	 
	
}
