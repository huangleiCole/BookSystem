package com.wz.bs.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wz.bs.entity.Admin;
import com.wz.bs.entity.Status;

public interface AdminMapper extends BasicMapper<Admin> {

	// 查找是否有超级管理员账号
	public Admin hasSuper();

	// 查询用户的密码是否是默认密码
	public Admin isDefault(String name);

	// 根据管理员名查找单个管理员
	public Admin selectOneByName(String name);

	// 模糊查询管理员
	public List<Admin> selectByMhName(String name);

	// 更多查询条件查找管理员
	public List<Admin> selectByCondition(@Param("startdate") Date start, @Param("enddate") Date end,
			@Param("phone") String phone, @Param("status") Status status);
}
