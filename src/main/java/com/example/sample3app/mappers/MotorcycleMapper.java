package com.example.sample3app.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.sample3app.beans.Motorcycle;
import com.example.sample3app.beans.SearchCondition;


@Mapper
public interface MotorcycleMapper {
	/**
     * バイク情報を検索条件で検索する。
     * @param condition 検索条件
     * @return バイク情報リスト
     */
    public List<Motorcycle> selectByCondition(SearchCondition condition);
    
    /*
     * バイク情報を主キーで検索する
     * @param バイク番号
     * @return バイク情報
     */
    public Motorcycle selectByPK(int motoNo);
}
