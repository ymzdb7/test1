package com.winhands.cshj.member.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 会员类
 * @author guojun
 */
public class Member {
    private String id;
    private String userName;
    private String password;
    private String salt;
    private String phoneNum;
    private String email;
    private String type;  //会员类别 0：注册用户 2：会员
    private Timestamp createTime;
    private Timestamp lastLoginTime;
    private Date expireTime;  //到期时间
    private Date lastRechargeTime;  //最后充值时间
    private String isVaild;  //是否有效 1 有效 2 冻结
    private String avatarPath;  //头像地址
    private String hasCompleted;  //是否完善过资料 1 否 2 是
    private String orgId1;//南通市
    private String orgId2;//区、县
    private String orgId3;//镇
    private String schoolId;//
    private String classId;//企业
    private String integration;//积分
    private String spare1;
    private String spare2;
    private String spare3;
    private String isStudent;//是否为在校生    0：否   1：是
    private String isCompleteStudent; //是否完善学生信息   0：否  1：是
    private String userNameCn;//学生姓名

    public String getHasCompleted() {
        return hasCompleted;
    }

    public void setHasCompleted(String hasCompleted) {
        this.hasCompleted = hasCompleted;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getIsVaild() {
        return isVaild;
    }

    public void setIsVaild(String isVaild) {
        this.isVaild = isVaild;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getLastRechargeTime() {
        return lastRechargeTime;
    }

    public void setLastRechargeTime(Date date) {
        this.lastRechargeTime = date;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgId1() {
		return orgId1;
	}

	public void setOrgId1(String orgId1) {
		this.orgId1 = orgId1;
	}

	public String getOrgId2() {
		return orgId2;
	}

	public void setOrgId2(String orgId2) {
		this.orgId2 = orgId2;
	}

	public String getOrgId3() {
		return orgId3;
	}

	public void setOrgId3(String orgId3) {
		this.orgId3 = orgId3;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getIntegration() {
		return integration;
	}

	public void setIntegration(String integration) {
		this.integration = integration;
	}

	public String getSpare1() {
		return spare1;
	}

	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}

	public String getSpare2() {
		return spare2;
	}

	public void setSpare2(String spare2) {
		this.spare2 = spare2;
	}

	public String getSpare3() {
		return spare3;
	}

	public void setSpare3(String spare3) {
		this.spare3 = spare3;
	}

	public String getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(String isStudent) {
		this.isStudent = isStudent;
	}

	public String getIsCompleteStudent() {
		return isCompleteStudent;
	}

	public void setIsCompleteStudent(String isCompleteStudent) {
		this.isCompleteStudent = isCompleteStudent;
	}

	public String getUserNameCn() {
		return userNameCn;
	}

	public void setUserNameCn(String userNameCn) {
		this.userNameCn = userNameCn;
	}
	
    
}
