package com.example.mapper;

import org.apache.ibatis.annotations.Param;

import com.example.domain.DemoDTO;

public interface DemoMapper {
	
	public void set(@Param("testColumn") String testColumn) throws Exception;

	public DemoDTO get(@Param("testColumn") String testColumn) throws Exception;
}
