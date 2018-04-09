package com.wz.bs.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.wz.bs.entity.Admin;
import com.wz.bs.entity.Status;
import com.wz.bs.entity.User;
import com.wz.bs.mapper.UserMapper;
import com.wz.bs.service.UserService;
import com.wz.bs.util.MyBatiesUtil;

public class UserServiceImpl implements UserService {

	SqlSession session = MyBatiesUtil.sqlSession();
	UserMapper userMapper = session.getMapper(UserMapper.class);
	User user=new User();
	
	@Override
	public Integer add(User user) {
		Integer t = userMapper.add(user);
		session.commit();
		return t;
	}

	@Override
	public Integer delete(Integer id) {
		Integer t = userMapper.delete(id);
		session.commit();
		return t;
	}

	@Override
	public Integer update(User user) {
		Integer t = userMapper.update(user);
		session.commit();
		return t;
	}

	@Override
	public User selectOneById(Integer id) {
		return userMapper.selectOneById(id);
	}
	
	@Override
	public List<User> selectByNo(String no) {
		return userMapper.selectByNo(no);
	}

	@Override
	public User selectOneByNo(String no) {
		return userMapper.selectOneByNo(no);
	}

	@Override
	public List<User> selectAll() {
		return userMapper.selectAll();
	}

	@Override
	public Integer disable(Integer id) {
		user.setId(id);
		user.setStatus(Status.禁用);
		Integer t = userMapper.update(user);
		session.commit();
		return t;
	}

	@Override
	public Integer able(Integer id) {
		user.setId(id);
		user.setStatus(Status.正常);
		Integer t = userMapper.update(user);
		session.commit();
		return t;
	}

	@Override
	public List<User> selectByCondition(String realname, Admin admin, Date start, Date end, String phone,
			Status status) {
		return userMapper.selectByCondition(realname, admin, start, end, phone, status);
	}

	

}
