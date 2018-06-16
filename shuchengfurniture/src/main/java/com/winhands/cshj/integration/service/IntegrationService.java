package com.winhands.cshj.integration.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.winhands.cshj.integration.entity.Integration;
import com.winhands.cshj.integration.entity.IntegrationMember;


@Component
public interface IntegrationService {
	 List<Integration> queryIntegrationList(Integration integration) throws Exception;
	 Integration queryIntegrationById(String id) throws Exception;
	 int saveIntegration(Integration integration) throws Exception;
	 int deleteIntegrationById(String id) throws Exception;
	 //查询积分日志
	 List<IntegrationMember> queryIntegrationMemberList(IntegrationMember integrationMember) throws Exception;
	 int saveIntegrationMember(IntegrationMember integrationMember) throws Exception;
	 int deleteIntegrationMember(String memberId) throws Exception;
	 
}
