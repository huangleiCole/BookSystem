package com.wz.bs.service;


import com.wz.bs.entity.Category;

public interface CategoryService extends BasicService<Category> {

	// 查询该分类有多少本图书
		public Integer selectCountByCategory(Integer id);
}
