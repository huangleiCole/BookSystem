package com.wz.bs.mapper;

import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.wz.bs.entity.Admin;
import com.wz.bs.entity.Book;
import com.wz.bs.entity.Lend;
import com.wz.bs.entity.LendStatus;
import com.wz.bs.entity.User;

public class LendMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testAdd() {
		SqlSession session = sqlSessionFactory.openSession();
		LendMapper lendMapper = session.getMapper(LendMapper.class);
		User user = new User();
		Admin admin = new Admin();
		Book book = new Book();
		user.setId(1);
		admin.setId(1);
		book.setId(1);
		Lend lend = new Lend(1, new Date(), null, 5, LendStatus.已借出, user, book, admin);
		Integer t = lendMapper.add(lend);
		if (t > 0) {
			session.commit();
			System.out.println("添加成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testDelete() {
		SqlSession session = sqlSessionFactory.openSession();
		LendMapper lendMapper = session.getMapper(LendMapper.class);
		Integer t = lendMapper.delete(1);
		if (t > 0) {
			session.commit();
			System.out.println("删除成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testUpdate() {
		SqlSession session = sqlSessionFactory.openSession();
		LendMapper lendMapper = session.getMapper(LendMapper.class);
		Lend lend = new Lend();
		lend.setId(2);
		lend.setCount(18);
		Integer t = lendMapper.update(lend);
		if (t > 0) {
			session.commit();
			System.out.println("更新成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testSelectOneById() {
		SqlSession session = sqlSessionFactory.openSession();
		LendMapper lendMapper = session.getMapper(LendMapper.class);
		System.out.println(lendMapper.selectOneById(2));
	}

	@Test
	public void testSelectAll() {
		SqlSession session = sqlSessionFactory.openSession();
		LendMapper lendMapper = session.getMapper(LendMapper.class);
		lendMapper.selectAll().forEach(System.out::println);
	}
}
