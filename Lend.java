package com.wz.bs.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lend {

	private Integer id;
	private Date lenddate;
	private Date returndate;
	private Integer count;
	private LendStatus lendstatus;
	private User user;
	private Book book;
	private Admin admin;
}
