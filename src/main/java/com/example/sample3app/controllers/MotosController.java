package com.example.sample3app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sample3app.beans.Brand;
import com.example.sample3app.beans.Motorcycle;
import com.example.sample3app.beans.SearchCondition;
import com.example.sample3app.services.MotosService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MotosController {
	
	@Autowired
	MotosService service;
	
	@GetMapping("/motos")
	public String motos(Model model) {
		//ブランド
		List<Brand> brands = new ArrayList<>();
		
		brands = service.getBrands();
		
		//バイク
		List<Motorcycle> motos = new ArrayList<>();
		SearchCondition condition = new SearchCondition();
		motos = service.getMotos(condition);
		
		model.addAttribute("brands", brands);
		model.addAttribute("motos", motos);
		
		log.debug("motos: {}", motos); // デバッグログ出力
		
		return "moto_list";
	}
}
