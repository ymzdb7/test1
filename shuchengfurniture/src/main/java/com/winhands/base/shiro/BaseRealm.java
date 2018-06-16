package com.winhands.base.shiro;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import com.winhands.cshj.member.entity.Member;
import com.winhands.cshj.member.service.MemberService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 

import com.winhands.base.exception.AuthenticationUtilException;
import com.winhands.base.user.entity.User;
import com.winhands.base.user.service.UserService;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.Encodes;

public class BaseRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(BaseRealm.class);
	@Autowired
	protected UserService userService;// 用户服务DB类
	@Autowired
	protected MemberService memberService;
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
//	private static final int SALT_SIZE = 8; 

	/**
	 * 认证回调函数,登录时调用.
	 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		if(token.getHost().equals("2")){   //客户端用户
			Member m = null;
			try {
				m = memberService.queryMemberByName(token.getUsername());
				if("2".equals(m.getIsVaild())){
					throw new AuthenticationUtilException(604, "该账号已被冻结!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("异常", e.getMessage());
				logger.error("查询用户权限时异常{},参数{}", "doGetAuthenticationInfo",token.getUsername());
				//throw new AuthenticationUtilException(603,"登录异常");
			}
			if(m != null){
					//密码通过校验后，
					byte[] salt =  Encodes.decodeHex(m.getSalt());// Digests.generateSalt(SALT_SIZE);
					//Hash sHash = new SimpleHash(HASH_ALGORITHM, new String(token.getPassword()), salt, HASH_INTERATIONS);
					try {
						SimpleAuthenticationInfo sa =  new SimpleAuthenticationInfo(new ShiroUser(
								m.getId(),
								m.getUserName(),
								m.getUserNameCn(),
								"",
								"2",
								m.getPhoneNum(),
								m.getEmail(),
								m.getOrgId1(),
								m.getOrgId2(),
								m.getOrgId3(),
								m.getSchoolId(),
								m.getClassId(),
								m.getIntegration(),
								m.getIsStudent(),
								m.getIsCompleteStudent()
								
								
						),
								m.getPassword(),
								ByteSource.Util.bytes(salt),
								getName());
						return sa;
					}catch (IncorrectCredentialsException e) {
						logger.error("异常", e.getMessage());
						throw new AuthenticationUtilException(603, "用户名和密码不匹配!");
					}
				}else {
				throw new AuthenticationUtilException(602, "此用户不存在,请重新输入!");
			}
		}
		User user = null;
		try {
			user = userService.findUserByLoginName(token.getUsername());
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("异常", e.getMessage());
			logger.error("查询用户权限时异常{},参数{}", "doGetAuthenticationInfo",token.getUsername());
			//throw new AuthenticationUtilException(603,"登录异常");
		}	
		if (user != null) { 
			//密码通过校验后，
			byte[] salt =  Encodes.decodeHex(user.getSalt());// Digests.generateSalt(SALT_SIZE); 
			//Hash sHash = new SimpleHash(HASH_ALGORITHM, new String(token.getPassword()), salt, HASH_INTERATIONS); 
			try {
				SimpleAuthenticationInfo sa =  new SimpleAuthenticationInfo(new ShiroUser(
						user.getUserId(),
						user.getUserName(), 
						user.getUserNameCn(),
						user.getUserPhone(),
						user.getUserType(),
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						""
						),
						user.getPassword(),
						ByteSource.Util.bytes(salt),
						getName());
				return sa;
			}catch (IncorrectCredentialsException e) {
				logger.error("异常", e.getMessage());
				throw new AuthenticationUtilException(603, "用户名和密码不匹配!");
			} 
		} else {
			throw new AuthenticationUtilException(602, "此用户不存在,请重新输入!");
		}
		
		
	}

	/**
	 * 授权查询回调函数, 进行鉴权当缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//	    User shiroUser = (User) principals.getPrimaryPrincipal(); 
		// 构建资源权限
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		UserRole userRole = new UserRole();
//		userRole.setUser_id(shiroUser.getUser_id());
		try {
//			List roleList = shiroUser.getRoleList();
//			Set<String> roleSet = new HashSet<String>();// 角色集合
//			Set<String> menuSet = new HashSet<String>();// 资源集合
//			if (roleList != null) {
//				for (int i = 0; i < roleList.size(); i++) {
//					Role role = (Role) roleList.get(i); 
//					roleSet.add(role.getRole_id());
//				}
//			}
//			info.addRoles(roleSet);// 添加角色到权限控制
//            
//			List menuList = menuConfService.getMenuListByUser(shiroUser);
//			if (menuList != null) {
//				for (int i = 0; i < menuList.size(); i++) {
//					MenuConf menus = (MenuConf) menuList.get(i);
//					menuSet.add(menus.getFunc_id());
//				}
//			}
//			info.addStringPermissions(menuSet);//// 添加资源到权限控制
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("异常", e.getMessage());
		}
		return info;
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
		matcher.setHashIterations(HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}
	
	
	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
	    SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
	    clearCachedAuthorizationInfo(principals);
	}
	 
	/**
	* 清除所有用户授权信息缓存.
	*/
	public void clearAllCachedAuthorizationInfo() {
	  Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
	  if (cache != null) {
			for (Object key : cache.keys()) {
			   cache.remove(key);
	        }
	   }
	}
	 
	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public String userId;
		public String userName;
		public String userNameCn;
		public String org_id;  
		public String user_type;
		public String userPhone;
		public String email;
		public String orgId1; 
		public String orgId2; 
		public String orgId3; 
		public String schoolId;
		public String classId;
		public String integration;
		public String isStudent;
		public String isCompleteStudent;
		
		public ShiroUser(String userId, String userName, String userNameCn,
			    String org_id, String user_type,
				String userPhone, String email, String orgId1, String orgId2,
				String orgId3, String schoolId, String classId,
				String integration, String isStudent, String isCompleteStudent) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.userNameCn = userNameCn;
			this.org_id = org_id;
			this.user_type = user_type;
			this.userPhone = userPhone;
			this.email = email;
			this.orgId1 = orgId1;
			this.orgId2 = orgId2;
			this.orgId3 = orgId3;
			this.schoolId = schoolId;
			this.classId = classId;
			this.integration = integration;
			this.isStudent = isStudent;
			this.isCompleteStudent = isCompleteStudent;
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (userName == null) {
				if (other.userName != null) {
					return false;
				}
			} else if (!userName.equals(other.userName)) {
				return false;
			}
			return true;
		}
	} 
	 
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/** get set 方法 */
	
	 

}
