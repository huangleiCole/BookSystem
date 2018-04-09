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
import com.wz.bs.entity.BookStatus;
import com.wz.bs.entity.Category;

public class BookMapperTest {

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
		BookMapper bookMapper = session.getMapper(BookMapper.class);
		Category category = new Category();
		category.setId(1);
		Admin admin = new Admin();
		admin.setId(2);
		Book book = new Book(1, "62014662", "红楼梦", "吴承恩", "人民出版社", 5, 3, new Date(), BookStatus.正常,
				category, admin);
		Integer t = bookMapper.add(book);
		if (t > 0) {
			session.commit();
			System.out.println("添加成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testDelete() {
		SqlSession session = sqlSessionFactory.openSession();
		BookMapper bookMapper = session.getMapper(BookMapper.class);
		Integer t = bookMapper.delete(2);
		if (t > 0) {
			session.commit();
			System.out.println("删除成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testUpdate() {
		SqlSession session = sqlSessionFactory.openSession();
		BookMapper bookMapper = session.getMapper(BookMapper.class);
		Book book = new Book();
		book.setId(3);
		book.setAuthor("曹雪芹");
		Integer t = bookMapper.update(book);
		if (t > 0) {
			session.commit();
			System.out.println("更新成功,受影响的行数:" + t);
		}
	}

	@Test
	public void testSelectOneById() {
		SqlSession session = sqlSessionFactory.openSession();
		BookMapper bookMapper = session.getMapper(BookMapper.class);
		System.out.println(bookMapper.selectOneById(3));
	}

	@Test
	public void testSelectOneByIsbn() {
		SqlSession session = sqlSessionFactory.openSession();
		BookMapper bookMapper = session.getMapper(BookMapper.class);
		System.out.println(bookMapper.selectOneByIsbn("62014662"));
	}

	@Test
	public void testSelectOneByName() {
		SqlSession session = sqlSessionFactory.openSession();
		BookMapper bookMapper = session.getMapper(BookMapper.class);
		System.out.println(bookMapper.selectOneByName("红楼梦"));
	}

	@Test
	public void testSelectByMhName() {
		SqlSession session = sqlSessionFactory.openSession();
		BookMapper bookMapper = session.getMapper(BookMapper.class);
		bookMapper.selectByMhName("红").forEach(System.out::println);
	}

	@Test
	public void testSelectAll() {
		SqlSession session = sqlSessionFactory.openSession();
		BookMapper bookMapper = session.getMapper(BookMapper.class);
		bookMapper.selectAll().forEach(System.out::println);
	}
}
