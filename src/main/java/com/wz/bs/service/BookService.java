package com.wz.bs.service;

import java.util.List;

import com.wz.bs.entity.Book;

public interface BookService extends BasicService<Book> {

	// 根据isbn查询图书
	public Book selectOneByIsbn(String isbn);

	// 根据图书名查询图书
	public Book selectOneByName(String name);

	// 根据图书名模糊查询图书
	public List<Book> selectByMhName(String name);

	// 图书下架
	public Integer down(Integer id);

}
