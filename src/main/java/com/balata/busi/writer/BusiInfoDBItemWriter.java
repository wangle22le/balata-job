package com.balata.busi.writer;

import com.balata.busi.bean.BusiInfoDo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BusiInfoDBItemWriter implements ItemWriter<BusiInfoDo> {
	private static final String INSERT_BUSI_INFO =
			"insert into BUSI_INFO(name, amt, status) values(?,?,?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void write(List<? extends BusiInfoDo> list) throws Exception {
		for(BusiInfoDo busiInfo : list){
			jdbcTemplate.update(INSERT_BUSI_INFO,
					busiInfo.getName(),
					busiInfo.getAmt(),
					busiInfo.getStatus());
		}
	}
}
