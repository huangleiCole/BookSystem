package com.wz.bs.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPojo {
	private String phone;
	private Date beginDate;
	private Date endDate;
	
}
