package com.example.sample3app.beans;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * バイク
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle {
	private Integer motoNo;
	private String motoName;
	private Integer seatHeight;
	private Integer cylinder;
	private String cooling;
	private Integer price;
	private String comments;
	private String brandId;
	private Integer version;
	private String insDt;
	private LocalDateTime updDt;
}
