package com.wz.bs.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.wz.bs.util.MyBatiesUtil;
import com.wz.bs.entity.Admin;
import com.wz.bs.entity.Status;
import com.wz.bs.mapper.AdminMapper;
import com.wz.bs.service.AdminService;

public class AdminServiceImpl implements AdminService {

	SqlSession session = MyBatiesUtil.sqlSession();
	AdminMapper adminMapper = session.getMapper(AdminMapper.class);
	Admin admin = new Admin();

	@Override
	public Integer add(Admin admin) {
		Integer t = adminMapper.add(admin);
		session.commit();
		return t;
	}

	@Override
	public Integer delete(Integer id) {
		Integer t = adminMapper.delete(id);
		session.commit();
		return t;
	}

	@Override
	public Integer update(Admin admin) {
		Integer t = adminMapper.update(admin);
		session.commit();
		return t;
	}

	@Override
	public Admin hasSuper() {
		return adminMapper.hasSuper();
	}

	@Override
	public Admin isDefault(String name) {
		return adminMapper.isDefault(name);
	}

	@Override
	public Admin selectOneById(Integer id) {
		return adminMapper.selectOneById(id);
	}

	@Override
	public Admin selectOneByName(String name) {
		return adminMapper.selectOneByName(name);
	}

	@Override
	public List<Admin> selectAll() {
		return adminMapper.selectAll();
	}

	@Override
	public List<Admin> selectByMhName(String name) {
		return adminMapper.selectByMhName(name);
	}

	@Override
	public Integer reset(Integer id) {
		admin.setId(id);
		admin.setPassword("670B14728AD9902AECBA32E22FA4F6BD");
		Integer t = adminMapper.update(admin);
		session.commit();
		return t;
	}

	@Override
	public Integer disable(Integer id) {
		admin.setId(id);
		admin.setStatus(Status.禁用);
		Integer t = adminMapper.update(admin);
		session.commit();
		return t;
	}

	@Override
	public Integer able(Integer id) {
		admin.setId(id);
		admin.setStatus(Status.正常);
		Integer t = adminMapper.update(admin);
		session.commit();
		return t;
	}

	@Override
	public List<Admin> selectByCondition(Date start, Date end, String phone, Status status) {
		return adminMapper.selectByCondition(start, end, phone, status);
	}

}
