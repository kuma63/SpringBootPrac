package com.example.sample3app.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.example.sample3app.beans.Motorcycle;
import com.example.sample3app.beans.SearchForm;


@Mapper
public interface MotorcycleMapper {
	/**
     * バイク情報を検索条件で検索する。
     * @param condition 検索条件
     * @return バイク情報リスト
     */
    public List<Motorcycle> selectByCondition(SearchForm condition);
    
    /*
     * バイク情報を主キーで検索する
     * @param バイク番号
     * @return バイク情報
     */
    public Motorcycle selectByPK(int motoNo);
    /**
     * バイク情報を更新する
     * @param moto バイク情報
     * @return 更新件数
     */
    @Update("UPDATE M_MOTORCYCLE SET MOTO_NAME = #{motoName}, SEAT_HEIGHT = #{seatHeight}, CYLINDER = #{cylinder}, COOLING = #{cooling}, PRICE = #{price}, COMMENTS = #{comments}, BRAND_ID = #{brandId}, VERSION = VERSION + 1 WHERE MOTO_NO = #{motoNo} AND VERSION = #{version}")
    public int update(Motorcycle moto);
    
    /**
     * 新しいバイク番号を採番する。
     * @return バイク番号
     */
    public Integer selectNewMotoNo();
    
    /**
     * バイク情報を登録する
     * @param moto バイク情報
     * @return 登録件数
     */
    public int insert(Motorcycle moto);
}
