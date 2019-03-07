package com.balata.busi.listener;

import com.balata.busi.bean.BusiInfoDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Service;


@Service
public class BusiInfoSkipListener implements SkipListener<BusiInfoDo, BusiInfoDo> {
	private static final Logger log = LoggerFactory.getLogger(BusiInfoSkipListener.class);

	@Override
	public void onSkipInProcess(BusiInfoDo BusiInfoDo, Throwable throwable) {
		log.info("BusiInfo was skipped in process: "+BusiInfoDo);
	}
	@Override
	public void onSkipInRead(Throwable arg0) {
	}
	@Override
	public void onSkipInWrite(BusiInfoDo BusiInfoDo, Throwable throwable) {
		log.info("BusiInfo was skipped in process: "+BusiInfoDo);
	}

}