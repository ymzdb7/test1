package com.winhands.cshj.member.service.impl;

import com.winhands.cshj.member.entity.Captcha;
import com.winhands.cshj.member.entity.Member;
import com.winhands.cshj.member.repository.MemberDao;
import com.winhands.cshj.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guojun
 */
@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberDao dao;
    @Override
    public Member queryMemberByName(String userName) throws Exception {
        return dao.queryMemberByName(userName);
    }

    @Override
    public int saveMember(Member m) throws Exception {
        return dao.saveMember(m);
    }

    @Override
    public int updateMember(Member m) throws Exception {
        return dao.updateMember(m);
    }

    @Override
    public List<Member> queryMemberList(Member m) throws Exception {
        return dao.queryMemberList(m);
    }

	@Override
	public List<Member> queryMemberFeeList(Member member) throws Exception {
		return dao.queryMemberFeeList(member);
	}

	@Override
	public List<Member> queryFeeCountList(Member member) throws Exception {
		return dao.queryFeeCountList(member);
	}

    @Override
    public Captcha queryCaptcha(String phoneNum) throws Exception {
        return dao.queryCaptcha(phoneNum);
    }

    @Override
    public int saveCaptcha(Captcha captcha) throws Exception {
        return dao.saveCaptcha(captcha);
    }

    @Override
    public int updateCaptcha(Captcha captcha) throws Exception {
        return dao.updateCaptcha(captcha);
    }

	@Override
	public Member queryMemberById(String id) throws Exception {
		return dao.queryMemberById(id);
	}

	@Override
	public  List<Member> queryMemberByEmail(String email) throws Exception {
		return dao.queryMemberByEmail(email);
	}

	@Override
	public List<Member> queryIntegrationCountListByClass(Member member)
			throws Exception {
		return dao.queryIntegrationCountListByClass(member);
	}

	@Override
	public List<Member> queryIntegrationCountListBySchool(Member member)
			throws Exception {
		return dao.queryIntegrationCountListBySchool(member);
	}

	@Override
	public int delteteMember(String id) throws Exception {
		return dao.delteteMember(id);
	}
}
