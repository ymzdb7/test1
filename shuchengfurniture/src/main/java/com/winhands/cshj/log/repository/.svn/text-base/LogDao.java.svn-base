package com.winhands.cshj.log.repository;

import com.winhands.base.service.MyBatisRepository;
import com.winhands.cshj.log.entity.Log;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author guojun
 */
@Component
public interface LogDao  extends MyBatisRepository{
    List<Log> queryLogList(Map<String,Object> map) throws Exception;
    int saveLog(Log log) throws Exception;
}
