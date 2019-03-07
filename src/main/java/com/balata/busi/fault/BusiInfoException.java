package com.balata.busi.fault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BusiInfoException extends Exception {
	private static final String INSERT_BUSI_ERR =
			"insert into busi_err(errmsg) values(?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public BusiInfoException(){
		super();
	}

	public BusiInfoException(String message, Throwable cause){
		super(message, cause);
		jdbcTemplate.update(INSERT_BUSI_ERR, message);
	}

	public BusiInfoException(String message){
		super(message);
		jdbcTemplate.update(INSERT_BUSI_ERR, message);
	}

	public BusiInfoException(Throwable cause){
		super(cause);
		jdbcTemplate.update(INSERT_BUSI_ERR, cause.getMessage());

	}
}
