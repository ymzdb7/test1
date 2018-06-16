package com.winhands.cshj.integration.service.impl;
  

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winhands.cshj.integration.entity.Integration;
import com.winhands.cshj.integration.entity.IntegrationMember;
import com.winhands.cshj.integration.repository.MBIntegrationDao;
import com.winhands.cshj.integration.service.IntegrationService;

@Service
@Transactional(readOnly=false)
public class IntegrationServiceImpl implements IntegrationService {
	private final Logger logger = LoggerFactory.getLogger(IntegrationServiceImpl.class);
	@Autowired
	private MBIntegrationDao mbIntegrationdao;
	@Override
	public List<Integration> queryIntegrationList(Integration integration)
			throws Exception {
		return mbIntegrationdao.queryIntegrationList(integration);
	}

	@Override
	public int saveIntegration(Integration integration) throws Exception {
		return mbIntegrationdao.saveIntegration(integration);
	}

	@Override
	public int deleteIntegrationById(String id) throws Exception {
		return mbIntegrationdao.deleteIntegration(id);
	}

	@Override
	public List<IntegrationMember> queryIntegrationMemberList(
			IntegrationMember integrationMember) throws Exception {
		return mbIntegrationdao.queryIntegrationMemberList(integrationMember);
	}

	@Override
	public int saveIntegrationMember(IntegrationMember integrationMember)
			throws Exception {
		return mbIntegrationdao.saveIntegrationMember(integrationMember);
	}

	@Override
	public Integration queryIntegrationById(String id) throws Exception {
		return mbIntegrationdao.queryIntegrationById(id);
	}

	@Override
	public int deleteIntegrationMember(String memberId) throws Exception {
		return mbIntegrationdao.deleteIntegrationMember(memberId);
	}
	
}
