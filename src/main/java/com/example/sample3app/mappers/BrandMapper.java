package com.example.sample3app.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.sample3app.beans.Brand;

@Mapper
public interface BrandMapper {
	public List<Brand> selectAll();
}
