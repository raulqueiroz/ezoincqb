package com.ezoinc.raulqueiroz.calculatrice.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezoinc.raulqueiroz.calculatrice.api.service.CalculatriceService;

@RestController
@RequestMapping("/calculatrice")
public class CalculatriceController {

	@Autowired
	private CalculatriceService service;

	@GetMapping(value = "/calculerExpressionMathematiques")
	public Double calculaExpressao(@RequestParam String expression) {
		return service.calculer(expression);
	}

}
