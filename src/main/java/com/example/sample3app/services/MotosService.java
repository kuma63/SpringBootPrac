package com.example.sample3app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
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
    
    /**
     * バイク情報を更新する
     * @param moto バイク情報
     * @return 更新件数
     */
	public int save(Motorcycle moto) {
		int cnt = mapper.update(moto);
		if(cnt == 0) {
			throw new OptimisticLockingFailureException("楽観的排他エラー");
		}
		return cnt;
		
	}
}
