package com.wz.bs.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wz.bs.entity.Admin;
import com.wz.bs.entity.Status;

public interface AdminService extends BasicService<Admin> {

	// 查询是否有超级管理员账户
	public Admin hasSuper();

	// 查询是否是默认密码
	public Admin isDefault(String name);

	// 根据管理员名查找单个管理员
	public Admin selectOneByName(String name);

	// 模糊查询管理员
	public List<Admin> selectByMhName(String name);

	// 重置密码
	public Integer reset(Integer id);

	// 禁用管理员
	public Integer disable(Integer id);

	// 恢复使用管理员
	public Integer able(Integer id);

	// 更多查询条件查找管理员
	public List<Admin> selectByCondition(@Param("startdate") Date start, @Param("enddate") Date end,
			@Param("phone") String phone, @Param("status") Status status);
}
