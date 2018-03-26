package com.wz.bs.entity;

import java.io.Serializable;
import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String password;
	private Date createdate;
	private Date lastlogin;
	private String realname;
	private String phone;
	private Flag flag;
	private Status status;

}
