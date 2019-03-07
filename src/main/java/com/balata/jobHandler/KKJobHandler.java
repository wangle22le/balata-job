package com.balata.jobHandler;

import com.balata.busi.config.BusiInfoJobConfig;
import com.balata.busi.config.TaskJobConfig;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 任务Handler的一个Demo（Bean模式）
 *
 * 开发步骤：
 * 1、继承 “IJobHandler” ；
 * 2、装配到Spring，例如加 “@Service” 注解；
 * 3、加 “@JobHander” 注解，注解value值为新增任务生成的JobKey的值;多个JobKey用逗号分割;
 * 4、执行日志：需要通过 "KKJobLogger.log" 打印执行日志；
 *
 * @author  2015-12-19 19:43:36
 */
@JobHandler(value="springbootJobHandler")
@Service
public class KKJobHandler extends IJobHandler {
    @Autowired
    TaskJobConfig taskJobConfig;
    @Autowired
    BusiInfoJobConfig busiInfoJobConfig;

	@Override
	public ReturnT<String> execute(String params) throws Exception {
		XxlJobLogger.log("ok!");

        busiInfoJobConfig.run();
		return ReturnT.SUCCESS;
	}



}
