package com.balata.busi.processor;

import com.balata.busi.bean.BusiInfoDo;
import com.balata.busi.fault.BusiInfoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

@Service
public class BusiInfoValidateProcessor implements ItemProcessor<BusiInfoDo, BusiInfoDo> {
	private static final Logger log = LoggerFactory.getLogger(BusiInfoValidateProcessor.class);

	@Override
	public BusiInfoDo process(BusiInfoDo BusiInfoDo) throws Exception {
		log.info(BusiInfoDo.toString());
		if(Double.parseDouble(BusiInfoDo.getAmt()) < 0){
			log.info("validate error: " + BusiInfoDo.toString());
            throw new BusiInfoException("validate error");
		}else{
			return BusiInfoDo;
		}
	}
}
