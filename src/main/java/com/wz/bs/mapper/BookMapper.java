package com.wz.bs.mapper;

import java.util.List;

import com.wz.bs.entity.Book;

public interface BookMapper extends BasicMapper<Book> {

	// 根据isbn查询图书
	public Book selectOneByIsbn(String isbn);

	// 根据图书名查询图书
	public Book selectOneByName(String name);

	// 根据图书名模糊查询图书
	public List<Book> selectByMhName(String name);

}
