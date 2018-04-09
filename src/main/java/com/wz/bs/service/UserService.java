package com.wz.bs.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wz.bs.entity.Admin;
import com.wz.bs.entity.Status;
import com.wz.bs.entity.User;

public interface UserService extends BasicService<User> {

	// 根据用户编号查找
	public List<User> selectByNo(String no);

	public User selectOneByNo(String no);

	// 禁用用户
	public Integer disable(Integer id);

	// 恢复使用
	public Integer able(Integer id);

	// 更多查询条件查找管理员
	public List<User> selectByCondition(@Param("realname") String realname, @Param("admin") Admin admin,
			@Param("startdate") Date start, @Param("enddate") Date end, @Param("phone") String phone,
			@Param("status") Status status);
}
