package com.example.sample3app.services;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sample3app.beans.Motorcycle;
import com.example.sample3app.beans.SearchCondition;

@SpringBootTest
public class MotosServiceTest {
	
	@Autowired
	MotosService service;
	
//	@Test
//	void バイク情報を全件検索できる() {
//		SearchCondition condition = new SearchCondition();
//		List<Motorcycle> motos = service.getMotos(condition);
//		
//		//検索結果の件数確認(上でとってきた件数が2か？)
//		assertThat(motos.size()).isEqualTo(2);
//		
//		//検索結果の各項目の確認
//		Motorcycle moto = motos.get(0);
//		assertThat(moto).isNotNull();
//		assertThat(moto.getMotoNo()).isEqualTo(1);
//		assertThat(moto.getMotoName()).isEqualTo("GB350");
//		assertThat(moto.getPrice()).isEqualTo(550000);
//		//assertThat(moto.getBrand().getBrandName()).isEqualTo("Honda");  エラー解決未
//	}
	
	@DisplayName("バイク一覧取得 条件: ブランドID")
	@ParameterizedTest
	@CsvSource({"01, GB350", "02, Z900RS"})
	void test001(String brandId, String motoName) {
		SearchCondition condition = new SearchCondition();
		condition.setBrandId(brandId);
		
		List<Motorcycle> motos = service.getMotos(condition);
		assertThat(motos.size()).isGreaterThanOrEqualTo(1); //1以上
		//assertThat(motos.size()).isGreaterThan(0);  0より大きい
		for(Motorcycle moto : motos) {
			assertThat(moto.getMotoName()).isEqualTo(motoName);
		}
	}
	
	@DisplayName("バイク一覧取得 条件: ブランドID 該当なし")
	@Test
	void test002() {
		SearchCondition condition = new SearchCondition();
		condition.setBrandId("99");
		
		List<Motorcycle> motos = service.getMotos(condition);
		assertThat(motos.size()).isEqualTo(0); 
	}

	@DisplayName("バイク一覧取得 条件: バイク名-完全一致")
	@ParameterizedTest
	@CsvSource({"GB350", "Z900RS"})
	void test003(String motoName) {
		SearchCondition condition = new SearchCondition();
		condition.setKeyword(motoName);
		
		List<Motorcycle> motos = service.getMotos(condition);
		assertThat(motos.size()).isGreaterThanOrEqualTo(1); //1以上
		//assertThat(motos.size()).isGreaterThan(0);  0より大きい
		for(Motorcycle moto : motos) {
			assertThat(moto.getMotoName()).isEqualTo(motoName);
		}
	}

//  バイク一覧取得 条件: バイク名-前方一致
//	バイク一覧取得 条件: バイク名-後方一致
//	バイク一覧取得 条件: バイク名-部分一致
	@DisplayName("バイク一覧取得 条件: バイク名-部分一致")
	@ParameterizedTest
	@CsvSource({"Z900R, Z900RS", "350, GB350", "be125, Rebe1250"})
	void test004(String keyword, String motoName) {
		SearchCondition condition = new SearchCondition();
		condition.setKeyword(keyword);
		
		List<Motorcycle> motos = service.getMotos(condition);
		assertThat(motos.size()).isGreaterThanOrEqualTo(1); //1以上
		//assertThat(motos.size()).isGreaterThan(0);  0より大きい
		for(Motorcycle moto : motos) {
			assertThat(moto.getMotoName()).isEqualTo(motoName);
		}
	}

	@DisplayName("バイク一覧取得 条件: バイク名 該当なし")
	@Test
	void test005() {
		SearchCondition condition = new SearchCondition();
		condition.setKeyword("AAA");
		
		List<Motorcycle> motos = service.getMotos(condition);
		assertThat(motos.size()).isEqualTo(0); 
	}
	
	@DisplayName("バイク一覧取得 条件: ブランドID, バイク名")
	@ParameterizedTest
	@CsvSource({"02, Z900R, Z900RS", "01, 350, GB350", "03, be125, Rebe1250"})
	void test006(String brandId, String keyword, String motoName) {
		SearchCondition condition = new SearchCondition();
		condition.setBrandId(brandId);
		condition.setKeyword(keyword);
		
		List<Motorcycle> motos = service.getMotos(condition);
		assertThat(motos.size()).isGreaterThanOrEqualTo(1); //1以上
		//assertThat(motos.size()).isGreaterThan(0);  0より大きい
		for(Motorcycle moto : motos) {
			assertThat(moto.getMotoName()).startsWith(motoName);
		}
	}

	@DisplayName("バイク一覧取得 条件: ブランドID, バイク名 該当なし")
	@Test
	void test007() {
		SearchCondition condition = new SearchCondition();
		condition.setBrandId("99");
		condition.setKeyword("AAA");
		
		List<Motorcycle> motos = service.getMotos(condition);
		assertThat(motos.size()).isEqualTo(0); 
	}

	@DisplayName("バイク一覧取得 条件: なし 全件該当")
	@Test
	void test008() {
		SearchCondition condition = new SearchCondition();
			
		List<Motorcycle> motos = service.getMotos(condition);
		assertThat(motos.size()).isEqualTo(3); 
	}

	@DisplayName("バイク情報取得 条件: バイク番号")
	@ParameterizedTest
	@CsvSource({"2, Z900RS", "1, GB350", "3, Rebe1250"})
	void test009(int motoNo, String motoName) {
		
		
		Motorcycle moto = service.getMotos(motoNo);
		assertThat(moto.getMotoName()).isEqualTo(motoName); 
		
	}
	
	@DisplayName("バイク情報取得 条件: バイク番号 全項目確認")
	@Test
	void test010() {
		Motorcycle moto = service.getMotos(1);
		assertThat(moto.getMotoNo()).isEqualTo(1);
		assertThat(moto.getMotoName()).isEqualTo("GB350");
		assertThat(moto.getSeatHeight()).isEqualTo(800);
		assertThat(moto.getCylinder()).isEqualTo(1);
		assertThat(moto.getCooling()).isEqualTo("空冷");
		assertThat(moto.getPrice()).isEqualTo(550000);
		assertThat(moto.getComments()).isEqualTo("エンジンのトコトコ音がいい。");
		assertThat(moto.getBrandId()).isEqualTo("01");
		assertThat(moto.getVersion()).isEqualTo(1);
	}

	@DisplayName("バイク情報更新")
	@Test
	void test011() {
		Motorcycle before = service.getMotos(1);
		before.setMotoName("motomoto");
		
		service.save(before); //更新(保存)
		
		Motorcycle after = service.getMotos(1); //変更後のバイク情報
		assertThat(after.getMotoName()).isEqualTo("motomoto");
		assertThat(after.getVersion()).isEqualTo(before.getVersion() + 1);
		
		
	}
}
