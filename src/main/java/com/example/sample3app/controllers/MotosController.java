package com.example.sample3app.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.sample3app.beans.Brand;
import com.example.sample3app.beans.Motorcycle;
import com.example.sample3app.beans.SearchForm;
import com.example.sample3app.forms.MotoForm;
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
	 * 更新画面の初期表示
	 * @param motoNo バイク番号
	 * @param motoForm 入力内容
	 * @param model model
	 * @return 遷移先
	 */
	@GetMapping("/motos/{motoNo}")
	public String initUpdate(@PathVariable("motoNo") Integer motoNo, @ModelAttribute MotoForm motoForm, Model model) {
		//ブランドリストを準備
		this.setBrands(model);
		
		//バイク番号を条件にバイク情報を取得
		Motorcycle moto = service.getMotos(motoNo);
		// 検索結果を入力内容に詰め替える
	    BeanUtils.copyProperties(moto, motoForm);
				
		return "moto";
	}
	
	@PostMapping("/motos/save")
	public String save(@ModelAttribute MotoForm motoForm, BindingResult result) {
		try {
			log.info("motoForm:{}", motoForm);
			Motorcycle moto = new Motorcycle();
			//入力内容を詰め替える
			BeanUtils.copyProperties(motoForm, moto);
			//情報を更新する
			int cnt = service.save(moto);
			log.info("{}件更新 motoForm:{}", cnt, motoForm);
			
			//リダイレクト(一覧に遷移)
			return "redirect:/motos";
			
		} catch (OptimisticLockingFailureException e) {
			result.addError(new ObjectError("global", e.getMessage()));
			return "moto";
			
		}
		
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
