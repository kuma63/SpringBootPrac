package com.example.sample3app.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sample3app.beans.Brand;
import com.example.sample3app.beans.Motorcycle;
import com.example.sample3app.beans.SearchForm;
import com.example.sample3app.services.MotosService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MotosController {
	
	@Autowired
	MotosService service;
	
	/**
	 * バイク一覧を表示する
	 * @param searchForm 検索条件
	 * @param model model
	 * @return 遷移先
	 */
	@GetMapping("/motos")
	public String motos(@Validated SearchForm searchForm, BindingResult result, Model model) {
		log.info("検索条件：{}", searchForm); //ログで入力した検索条件を受け取れてるか確認できる
		if(result.hasErrors()) {
			//入力チェックエラーがある場合
			return "moto_list";
		}
		
		//ブランドリストを準備
		this.setBrands(model);
		
		//バイク
		List<Motorcycle> motos = new ArrayList<>();
		motos = service.getMotos(searchForm);
		
		model.addAttribute("motos", motos);
		model.addAttribute("datetime", LocalDateTime.now());
		
		log.debug("motos: {}", motos); // デバッグログ出力
		
		return "moto_list";
	}
	
	/**
	 * 検索条件をクリアする
	 * @param searchForm 検索条件
	 * @param model model
	 * @return 遷移先
	 */
	@GetMapping("/motos/reset")
	public String reset(SearchForm searchForm, Model model) {
		//ブランドリストを準備
		this.setBrands(model);
		
		//検索条件のクリア
		searchForm = new SearchForm();
		return "moto_list";
	}
	
	/**
	 * ブランドリストをmodelにセットする
	 * @param model
	 */
	private void setBrands(Model model) {
		List<Brand> brands = new ArrayList<>();	
		brands = service.getBrands();
		model.addAttribute("brands", brands);
	}
}
