package com.balata.busi.processor;

import com.balata.busi.bean.BusiInfoDo;
import com.balata.busi.bean.BusiInfoOutDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusiInfoItemProcessor implements ItemProcessor<BusiInfoDo, BusiInfoOutDo> {

	private static final Logger log = LoggerFactory.getLogger(BusiInfoItemProcessor.class);

	private static final String STATUS_SECCESS = "00";
	private static final String STATUS_FAILURE = "01";

    @Autowired
    private BusiInfoValidateProcessor busiInfoValidateProcessor;

	@Override
	public BusiInfoOutDo process(BusiInfoDo BusiInfoDo) throws Exception {
        BusiInfoOutDo BusiInfoDoOut = new BusiInfoOutDo();
		BusiInfoDoOut.setName(BusiInfoDo.getName());
		BusiInfoDoOut.setAmt(BusiInfoDo.getAmt());
		BusiInfoDoOut.setStatus(STATUS_SECCESS);
		log.info(BusiInfoDoOut.toString());
		return BusiInfoDoOut;
	}
}
