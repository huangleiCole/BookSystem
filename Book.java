package com.wz.bs.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String isbn;
	private String name;
	private String author;
	private String publishhouse;
	private Integer count;
	private Integer restcount;
	private Date ondate;
	private BookStatus bookstatus;
	private Category category;
	private Admin admin;
}
