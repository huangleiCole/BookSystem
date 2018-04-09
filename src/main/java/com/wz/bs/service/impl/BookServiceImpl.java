package com.wz.bs.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.wz.bs.entity.Book;
import com.wz.bs.entity.BookStatus;
import com.wz.bs.mapper.BookMapper;
import com.wz.bs.service.BookService;
import com.wz.bs.util.MyBatiesUtil;

public class BookServiceImpl implements BookService {

	SqlSession session = MyBatiesUtil.sqlSession();
	BookMapper bookMapper = session.getMapper(BookMapper.class);
	Book book = new Book();

	@Override
	public Integer add(Book book) {
		Integer t = bookMapper.add(book);
		session.commit();
		return t;
	}

	@Override
	public Integer delete(Integer id) {
		Integer t = bookMapper.delete(id);
		session.commit();
		return t;
	}

	@Override
	public Integer update(Book book) {
		Integer t = bookMapper.update(book);
		session.commit();
		return t;
	}

	@Override
	public Book selectOneById(Integer id) {
		this.session.clearCache();
		return bookMapper.selectOneById(id);
	}

	@Override
	public Book selectOneByIsbn(String isbn) {
		this.session.clearCache();
		return bookMapper.selectOneByIsbn(isbn);
	}

	@Override
	public Book selectOneByName(String name) {
		this.session.clearCache();
		return bookMapper.selectOneByName(name);
	}

	@Override
	public List<Book> selectByMhName(String name) {
		this.session.clearCache();
		return bookMapper.selectByMhName(name);
	}

	@Override
	public List<Book> selectAll() {
		this.session.clearCache();
		return bookMapper.selectAll();
	}

	@Override
	public Integer down(Integer id) {
		book.setId(id);
		book.setBookstatus(BookStatus.下架);
		Integer t = bookMapper.update(book);
		session.commit();
		return t;
	}


}
