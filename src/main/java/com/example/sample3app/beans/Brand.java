package com.example.sample3app.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * バイクメーカーブランド
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

	private String brandId;
    private String brandName;
	
}
