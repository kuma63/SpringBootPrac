package com.example.sample3app.forms;

import lombok.Data;

/**
 * 
 * 更新画面の入力内容
 *
 */
@Data
public class MotoForm {
	private Integer motoNo;
	private String motoName;
	private Integer seatHeight;
	private Integer cylinder;
	private String cooling;
	private Integer price;
	private String comments;
	//private Brand brand;
	private String brandId;
	private Integer version;
}
