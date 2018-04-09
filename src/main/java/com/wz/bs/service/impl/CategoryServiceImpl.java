package com.wz.bs.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.wz.bs.entity.Category;
import com.wz.bs.mapper.CategoryMapper;
import com.wz.bs.service.CategoryService;
import com.wz.bs.util.MyBatiesUtil;

public class CategoryServiceImpl implements CategoryService {

	SqlSession session = MyBatiesUtil.sqlSession();
	CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);

	@Override
	public Integer add(Category category) {
		Integer t = categoryMapper.add(category);
		session.commit();
		return t;
	}

	@Override
	public Integer delete(Integer id) {
		Integer t = categoryMapper.delete(id);
		session.commit();
		return t;
	}

	@Override
	public Integer update(Category category) {
		Integer t = categoryMapper.update(category);
		session.commit();
		return t;
	}

	@Override
	public Category selectOneById(Integer id) {
		this.session.clearCache();
		return categoryMapper.selectOneById(id);
	}

	@Override
	public List<Category> selectAll() {
		this.session.clearCache();
		return categoryMapper.selectAll();
	}

	@Override
	public Integer selectCountByCategory(Integer id) {
		this.session.clearCache();
		return categoryMapper.selectCountByCategory(id);
	}

}
