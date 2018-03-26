package com.wz.bs.mapper;

import com.wz.bs.entity.Category;

public interface CategoryMapper extends BasicMapper<Category> {

	// 查询该分类有多少本图书
	public Integer selectCountByCategory(Integer id);

}
