package com.example.sample3app.services;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sample3app.beans.Brand;
import com.example.sample3app.beans.Motorcycle;
import com.example.sample3app.beans.SearchForm;
import com.example.sample3app.mappers.BrandMapper;
import com.example.sample3app.mappers.MotorcycleMapper;

@Service
public class MotosService {
	
	//messageSourceを使ってメッセージを取り出す
	@Autowired
	MessageSource messageSource;
	
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
    @Transactional
	public int save(Motorcycle moto) {
		int cnt = mapper.update(moto);
		if(cnt == 0) {
			throw new OptimisticLockingFailureException(
					messageSource.getMessage("error.optimisticLockingFailure", 
					null, Locale.JAPANESE));
		}
		
		// 2件以上更新は想定外（SQLの不備の可能性）
	    if (cnt > 1) {
	        throw new RuntimeException(
	        		messageSource.getMessage("error.runtimeexception", 
	        		new String[] { "2件以上更新されました。" }, Locale.JAPANESE));
	    }
		return cnt;
		
	}
}
