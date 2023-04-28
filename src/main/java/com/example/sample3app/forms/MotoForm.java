package com.example.sample3app.forms;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 
 * 更新画面の入力内容
 *
 */
@Data
public class MotoForm {
	private Integer motoNo;
	@NotBlank
	private String motoName;
	
	@Min(0)
	@Max(1000)
	private Integer seatHeight;
	
	@Min(1)
    @Max(4)
	private Integer cylinder;
	
	private String cooling;
	
	@Min(100000)
	private Integer price;
	
	private String comments;
	//private Brand brand;
	private String brandId;
	private Integer version;
}
