package com.balata.busi.config;

import com.balata.busi.bean.BusiInfoDo;
import com.balata.busi.bean.BusiInfoOutDo;
import com.balata.busi.listener.BusiInfoSkipListener;
import com.balata.busi.processor.BusiInfoItemProcessor;
import com.balata.busi.processor.BusiInfoValidateProcessor;
import com.balata.busi.reader.BusiInfoFileItemReader;
import com.balata.busi.writer.BusiInfoDBItemWriter;
import com.balata.busi.writer.BusiInfoFileItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BusiInfoJobConfig {
	@Autowired
	public JobLauncher jobLauncher;

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private BusiInfoFileItemReader busiInfoFileItemReader;

	@Autowired
	private BusiInfoItemProcessor busiInfoItemProcessor;

	@Autowired
	private BusiInfoFileItemWriter busiInfoFileItemWriter;

	@Autowired
	private BusiInfoDBItemWriter busiInfoDBItemWriter;

	@Autowired
	private BusiInfoSkipListener listener;

	public void run() {
		try {
			String dateParam = new Date().toString();
			JobParameters param =    new JobParametersBuilder().addString("date", dateParam).toJobParameters();
			System.out.println(dateParam);
			JobExecution execution = jobLauncher.run(importJob(), param);             //执行job
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	public Job importJob() {
		return jobBuilderFactory.get("importJob")
				.incrementer(new RunIdIncrementer())
				.flow(BusiInfoStep2())
//				.next(step2())
                .end()
				.build();
	}

	@Bean
	public Step BusiInfoStep1() {
		return stepBuilderFactory.get("BusiInfoStep1")
				.<BusiInfoDo, BusiInfoOutDo> chunk(10)
				.reader(busiInfoFileItemReader.getMultiBusiInfoReader())
				.processor(busiInfoItemProcessor)
				.writer(busiInfoFileItemWriter.getBusiInfoItemWriter())
				.build();
	}


	@Bean
	public Step BusiInfoStep2() {
		return stepBuilderFactory.get("BusiInfoStep2")
                .<BusiInfoDo,BusiInfoDo> chunk(10)
				.reader(busiInfoFileItemReader.getMultiBusiInfoReader())
				.writer(busiInfoDBItemWriter)
//				.faultTolerant()
//				.skipLimit(20)
//				.skip(Exception.class)
//				.listener(listener)
//				.retryLimit(3)
//				.retry(RuntimeException.class)
				.build();
	}

	@Bean
	public Step BusiInfoStep3() {
		CompositeItemProcessor<BusiInfoDo,BusiInfoOutDo> compositeItemProcessor = new CompositeItemProcessor<BusiInfoDo,BusiInfoOutDo>();
		List compositeProcessors = new ArrayList();
		compositeProcessors.add(new BusiInfoValidateProcessor());
		compositeProcessors.add(new BusiInfoItemProcessor());
		compositeItemProcessor.setDelegates(compositeProcessors);
		return stepBuilderFactory.get("BusiInfoStep3")
				.<BusiInfoDo, BusiInfoOutDo> chunk(10)
				.reader(busiInfoFileItemReader.getMultiBusiInfoReader())
				.processor(compositeItemProcessor)
                .faultTolerant()
				.skipLimit(2)
                .skip(Exception.class)
				.writer(busiInfoFileItemWriter.getBusiInfoItemWriter())
                .taskExecutor(taskExecutor())
                .throttleLimit(4)
                .build();
	}

	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setMaxPoolSize(20);
		taskExecutor.setQueueCapacity(200);
		taskExecutor.setKeepAliveSeconds(60);
		taskExecutor.setThreadNamePrefix("ThreadPoolTaskExecutor-TaskExecutor-");
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}

	@Bean
	public Step BusiInfoStep4() {
		return stepBuilderFactory.get("BusiInfoStep4")
				.<BusiInfoDo, BusiInfoOutDo> chunk(10)
				.reader(busiInfoFileItemReader.getMultiBusiInfoReader())
				.processor(busiInfoItemProcessor)
				.writer(busiInfoFileItemWriter.getBusiInfoItemWriter())
				.taskExecutor(taskExecutor())
				.throttleLimit(4)
				.build();
	}

}
