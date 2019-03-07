package com.balata.busi.writer;

import com.balata.busi.bean.BusiInfoOutDo;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;


@Service
public class BusiInfoFileItemWriter {

	public FlatFileItemWriter<BusiInfoOutDo> getBusiInfoItemWriter() {
		FlatFileItemWriter<BusiInfoOutDo> txtItemWriter = new FlatFileItemWriter<BusiInfoOutDo>();
		txtItemWriter.setAppendAllowed(true);
		txtItemWriter.setShouldDeleteIfExists(true);
		txtItemWriter.setEncoding("UTF-8");
		txtItemWriter.setResource(new FileSystemResource("balata-job/data/OutPut/BusiInfoFileWriterData.txt"));
		txtItemWriter.setLineAggregator(new DelimitedLineAggregator<BusiInfoOutDo>() {{
			setDelimiter(",");
			setFieldExtractor(new BeanWrapperFieldExtractor<BusiInfoOutDo>() {{
				setNames(new String[]{"name", "amt", "status" });
			}});
		}});
		return txtItemWriter;
	}
}
