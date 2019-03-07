package com.balata.busi.reader;

import com.balata.busi.bean.BusiInfoDo;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class BusiInfoFileItemReader{

	private FlatFileItemReader<BusiInfoDo> reader;

	public FlatFileItemReader<BusiInfoDo> getFileItemReader() {
		reader = new FlatFileItemReader<BusiInfoDo>();
		reader.setLineMapper(new DefaultLineMapper<BusiInfoDo>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(new String[] { "name", "amt"});
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<BusiInfoDo>() {{
				setTargetType(BusiInfoDo.class);
			}});
		}});
		reader.setLinesToSkip(1);
		return reader;
	}

	public MultiResourceItemReader<BusiInfoDo> getMultiBusiInfoReader() {
		MultiResourceItemReader<BusiInfoDo> reader = new MultiResourceItemReader<BusiInfoDo>();
		Resource[] files = new Resource[]{new FileSystemResource("data/busi_info/BusiInfoFileReadData.csv")};
		reader.setResources(files);
		reader.setDelegate(this.getFileItemReader());

		return reader;
	}
}
