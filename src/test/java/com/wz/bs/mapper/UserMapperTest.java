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
import com.wz.bs.entity.Status;
import com.wz.bs.entity.User;
import com.wz.bs.util.DateUtil;

public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testAddUser() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		Admin admin = new Admin();
		admin.setId(1);
		User user = new User(3, "vz55373", new Date(), "小明", "16666666", Status.正常, admin);
		Integer t = userMapper.add(user);
		if (t > 0) {
			session.commit();
			System.out.println("添加成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testDeleteUser() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		Integer t = userMapper.delete(1);
		if (t > 0) {
			session.commit();
			System.out.println("删除成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testUpdateUser() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		User user = new User();
		user.setId(2);
		Integer t = userMapper.update(user);
		if (t > 0) {
			session.commit();
			System.out.println("更新成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testSelectOneById() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.println(userMapper.selectOneById(2));
	}

	@Test
	public void testSelectOneByNo() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.println(userMapper.selectOneByNo("WZ0840219434"));
	}

	@Test
	public void testSelectByNo() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		userMapper.selectByNo("WZ0840219434").forEach(System.out::println);
	}

	@Test
	public void testSelectAll() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		userMapper.selectAll().forEach(System.out::println);
	}

	@Test
	public void testSelectByCondition() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		List<User> users = userMapper.selectByCondition(null, null, DateUtil.build(2018, 02, 15),
				DateUtil.build(2018, 03, 01), null, null);
		users.forEach(System.out::println);
	}
}
