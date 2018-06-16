package com.winhands.cshj.member.service;

import com.winhands.cshj.member.entity.Captcha;
import com.winhands.cshj.member.entity.Member;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author guojun
 */
@Component
public interface MemberService {
    Member queryMemberByName(String userName) throws Exception;
    List<Member> queryMemberByEmail(String email) throws Exception;
    Member queryMemberById(String id) throws Exception;
    int saveMember(Member m) throws  Exception;
    int updateMember(Member m) throws Exception;
    int delteteMember(String id) throws Exception;
    List<Member> queryMemberList(Member m) throws Exception;
    //付费详单
    List<Member>queryMemberFeeList(Member member)throws Exception;
    //付费统计
    List<Member>queryFeeCountList(Member member)throws Exception;
    //按班级积分统计
    List<Member>queryIntegrationCountListByClass(Member member)throws Exception;
    //按学校积分统计
    List<Member>queryIntegrationCountListBySchool(Member member)throws Exception;
    Captcha queryCaptcha(String phoneNum) throws Exception;
    int saveCaptcha(Captcha captcha) throws Exception;
    int updateCaptcha(Captcha captcha) throws Exception;
}
