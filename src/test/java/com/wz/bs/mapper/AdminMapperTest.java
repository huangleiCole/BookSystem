package com.wz.bs.mapper;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.wz.bs.entity.Admin;
import com.wz.bs.entity.Flag;
import com.wz.bs.entity.Status;
import com.wz.bs.util.DateUtil;
import com.wz.bs.util.MD5Code;

public class AdminMapperTest {

	private SqlSessionFactory sqlSessionFactory;
	private MD5Code MD5;

	@Before
	public void setUp() throws Exception {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		MD5 = new MD5Code();
	}

	@Test
	public void testAdd() {
		SqlSession session = sqlSessionFactory.openSession();
		AdminMapper adminMapper = session.getMapper(AdminMapper.class);
		Admin admin = new Admin(1, "jack5", MD5.getMD5ofStr("000000"), new Date(), null, "j", "18888888888",
				Flag.超级管理员, Status.正常);
		Integer t = adminMapper.add(admin);
		if (t > 0) {
			session.commit();
			System.out.println("添加成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testDelete() {
		SqlSession session = sqlSessionFactory.openSession();
		AdminMapper adminMapper = session.getMapper(AdminMapper.class);
		Integer t = adminMapper.delete(3);
		if (t > 0) {
			session.commit();
			System.out.println("删除成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testUpdate() {
		SqlSession session = sqlSessionFactory.openSession();
		AdminMapper adminMapper = session.getMapper(AdminMapper.class);
		Admin admin = new Admin(1, "nini", "666666", new Date(), new Date(), "jj", "14325252", Flag.普通管理员, Status.正常);
		Integer t = adminMapper.update(admin);
		if (t > 0) {
			session.commit();
			System.out.println("更新成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testHasSuper() {
		SqlSession session = sqlSessionFactory.openSession();
		AdminMapper adminMapper = session.getMapper(AdminMapper.class);
		Admin t = adminMapper.hasSuper();
		System.out.println(t);
	}

	@Test
	public void testIsDefault() {
		SqlSession session = sqlSessionFactory.openSession();
		AdminMapper adminMapper = session.getMapper(AdminMapper.class);
		System.out.println(adminMapper.isDefault("jack1"));
	}

	@Test
	public void testSelectOneById() {
		SqlSession session = sqlSessionFactory.openSession();
		AdminMapper adminMapper = session.getMapper(AdminMapper.class);
		Admin admin = adminMapper.selectOneById(3);
		System.out.println(admin);
	}

	@Test
	public void testSelectOneByName() {
		SqlSession session = sqlSessionFactory.openSession();
		AdminMapper adminMapper = session.getMapper(AdminMapper.class);
		Admin admin = adminMapper.selectOneByName("tom");
		System.out.println(admin);
	}

	@Test
	public void testSelectAll() {
		SqlSession session = sqlSessionFactory.openSession();
		AdminMapper adminMapper = session.getMapper(AdminMapper.class);
		adminMapper.selectAll().forEach(System.out::println);
	}

	@Test
	public void testSelectByMhName() {
		SqlSession session = sqlSessionFactory.openSession();
		AdminMapper adminMapper = session.getMapper(AdminMapper.class);
		adminMapper.selectByMhName("k").forEach(System.out::println);
	}

	@Test
	public void testSelectByCondition() {
		SqlSession session = sqlSessionFactory.openSession();
		AdminMapper adminMapper = session.getMapper(AdminMapper.class);
		List<Admin> admins = adminMapper.selectByCondition(DateUtil.build(2018, 02, 13), DateUtil.build(2018, 02, 16),
				null, null);
		admins.forEach(System.out::println);
	}

}
