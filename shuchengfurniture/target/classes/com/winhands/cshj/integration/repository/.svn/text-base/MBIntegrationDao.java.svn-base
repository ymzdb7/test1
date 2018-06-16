package com.winhands.cshj.integration.repository;




import java.util.List;

import org.springframework.stereotype.Component;

import com.winhands.base.service.MyBatisRepository;
import com.winhands.cshj.integration.entity.Integration;
import com.winhands.cshj.integration.entity.IntegrationMember;

/**
 * 积分管理数据库操作层 基于mybais
 * @author yuanl
 *
 */  
@Component
public interface MBIntegrationDao extends MyBatisRepository{
	List<Integration> queryIntegrationList(Integration integration) throws Exception;
	Integration queryIntegrationById(String id) throws Exception;
	int saveIntegration(Integration integration) throws Exception;
	int deleteIntegration(String id) throws Exception;
	//查询积分日志
	List<IntegrationMember> queryIntegrationMemberList(IntegrationMember integrationMember) throws Exception;
	int saveIntegrationMember(IntegrationMember integrationMember) throws Exception;
	int deleteIntegrationMember(String memberId) throws Exception;
}
