package com.wz.bs.mapper;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.wz.bs.entity.Admin;
import com.wz.bs.entity.Category;

public class CategoryMapperTest {

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
		CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
		Admin admin = new Admin();
		admin.setId(4);
		Category category = new Category(1, "天文类",null, admin);
		Integer t = categoryMapper.add(category);
		if (t > 0) {
			session.commit();
			System.out.println("添加成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testDelete() {
		SqlSession session = sqlSessionFactory.openSession();
		CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
		Integer t = categoryMapper.delete(1);
		if (t > 0) {
			session.commit();
			System.out.println("删除成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testUpdate() {
		SqlSession session = sqlSessionFactory.openSession();
		CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
		Admin admin = new Admin();
		admin.setId(4);
		Category category = new Category(2, "文学类",null, admin);
		Integer t = categoryMapper.update(category);
		if (t > 0) {
			session.commit();
			System.out.println("更新成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testSelectOneById() {
		SqlSession session = sqlSessionFactory.openSession();
		CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
		System.out.println(categoryMapper.selectOneById(2));
	}

	@Test
	public void testSelectAll() {
		SqlSession session = sqlSessionFactory.openSession();
		CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
		categoryMapper.selectAll().forEach(System.out::println);
	}

}
