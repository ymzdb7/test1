package com.winhands.cshj.log.service.impl;

import com.winhands.cshj.log.entity.Log;
import com.winhands.cshj.log.repository.LogDao;
import com.winhands.cshj.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author guojun
 */
@Service
public class LogServiceImpl implements LogService{
    @Autowired
    private LogDao dao;
    @Override
    public List<Log> queryLogList(Map<String, Object> map) throws Exception {
        return dao.queryLogList(map);
    }

    @Override
    public int saveLog(Log log) throws Exception {
        return dao.saveLog(log);
    }
}
