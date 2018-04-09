package com.wz.bs.mapper;

import java.io.Serializable;
import java.util.List;

public interface BasicMapper<T> {
	// 添加
	public Integer add(T instance);

	// 删除
	public Integer delete(Serializable id);

	// 更新
	public Integer update(T instance);

	// 根据id查
	public T selectOneById(Serializable id);

	// 查询所有
	public List<T> selectAll();
}
