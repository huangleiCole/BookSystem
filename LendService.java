package com.wz.bs.service;

import com.wz.bs.entity.Lend;

public interface LendService extends BasicService<Lend>{

	
	//归还
	public Integer returnbook(Integer id);
}
