package com.wz.bs.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.wz.bs.entity.Lend;
import com.wz.bs.entity.LendStatus;
import com.wz.bs.mapper.LendMapper;
import com.wz.bs.service.LendService;
import com.wz.bs.util.MyBatiesUtil;

public class LendServiceImpl implements LendService {

	SqlSession session = MyBatiesUtil.sqlSession();
	LendMapper lendMapper = session.getMapper(LendMapper.class);
	Lend lend = new Lend();

	@Override
	public Integer add(Lend lend) {
		Integer t = lendMapper.add(lend);
		session.commit();
		return t;
	}

	@Override
	public Integer delete(Integer id) {
		Integer t = lendMapper.delete(id);
		session.commit();
		return t;
	}

	@Override
	public Integer update(Lend lend) {
		Integer t = lendMapper.update(lend);
		session.commit();
		return t;
	}

	@Override
	public Lend selectOneById(Integer id) {
		this.session.clearCache();
		return lendMapper.selectOneById(id);
	}

	@Override
	public List<Lend> selectAll() {
		this.session.clearCache();
		return lendMapper.selectAll();
	}

	@Override
	public Integer returnbook(Integer id) {
		Lend lend = new Lend();
		lend.setId(id);
		lend.setLendstatus(LendStatus.已归还);
		Integer t = lendMapper.update(lend);
		session.commit();
		return t;
	}

}
