package com.winhands.base.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winhands.base.user.entity.User;
import com.winhands.base.user.repository.MBUserDao;
import com.winhands.base.user.repository.UserDao;
import com.winhands.base.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserDao userdao;
	@Autowired
    private MBUserDao mBUserDao;
	
	@Override
	public User findUserByLoginName(String username) {  
		return userdao.findUserByUserName(username);
	}

	@Override
	public List<User> findUserList(Map<String, Object> conditions) {
		return mBUserDao.queryUserList(conditions);
	}

	@Override
	public User save(User user) { 
		return userdao.save(user);
	}

	@Override
	public void deleteByUserId(String userId) {
		  userdao.delete(userId);
	}

	@Override
	public User findUserByUserId(String userId) {
		return userdao.findOne(userId);
	}

	@Override
	public List<User> queryChefList(Map<String, Object> conditions) {
		return mBUserDao.queryChefList(conditions);
	}

	@Override
	public User findUserByOrgId(Map<String, Object> conditions) {
		List <User>userList=mBUserDao.findUserByOrgId(conditions);
		User user=new User();
		if(userList!=null||userList.size()==0){
			 user=userList.get(0);
			return user;
		}else{
			return user;
		}
	}

    @Override
    public int updateLastLoginByUserId(String userId) throws Exception{
        return mBUserDao.updateLastLoginByUserId(userId);
    }
}
