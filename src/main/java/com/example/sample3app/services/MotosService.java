package com.example.sample3app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample3app.beans.Brand;
import com.example.sample3app.beans.Motorcycle;
import com.example.sample3app.beans.SearchForm;
import com.example.sample3app.mappers.BrandMapper;
import com.example.sample3app.mappers.MotorcycleMapper;

@Service
public class MotosService {
	@Autowired
    MotorcycleMapper mapper;
	
	@Autowired
	BrandMapper brandMapper;
    
    public List<Motorcycle> getMotos(SearchForm condition) {
        return mapper.selectByCondition(condition);
       
    }
    
    public Motorcycle getMotos(int motoNo) {
        return mapper.selectByPK(motoNo);
       
    }
    
    public List<Brand> getBrands(){
    	return brandMapper.selectAll();
    }

	public int save(Motorcycle moto) {
		return mapper.update(moto);
		
	}
}
