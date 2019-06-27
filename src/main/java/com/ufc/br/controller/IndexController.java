package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;
import com.ufc.br.repository.PratoRepository;
import com.ufc.br.service.PratoService;

@Controller
public class IndexController implements ErrorController{
	@Autowired
	private PratoService pratoService;

	@RequestMapping("/")
	public ModelAndView index() {
		List<Prato> pratos = pratoService.listarPratos();
		ModelAndView andView = new ModelAndView("index");
		andView.addObject("pratos", pratos);
		return andView;
	}
	
	@RequestMapping("/error")
	public ModelAndView error() {
		List<Prato> pratos = pratoService.listarPratos();
		ModelAndView andView = new ModelAndView("index");
		andView.addObject("pratos", pratos);
		return andView;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	

}
