package com.wz.bs.service;

import java.util.List;

public interface BasicService<T> {
	// 添加
	public Integer add(T instance);

	// 删除
	public Integer delete(Integer id);

	// 更新
	public Integer update(T instance);

	// 根据id查
	public T selectOneById(Integer id);

	// 查询所有
	public List<T> selectAll();
	
}
