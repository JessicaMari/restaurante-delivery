package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufc.br.carrinho.Carrinho;
import com.ufc.br.model.Prato;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private Carrinho carrinho;

	@GetMapping
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("carrinho/listac");
		mv.addObject("pratos", carrinho.list());
		mv.addObject("quantidade", carrinho.getQuantidade());
		mv.addObject("valorTotal", carrinho.calcularValorTotal());
		return mv;
	}

	@GetMapping("/add/{idPrato}")
	public String addPrato(@PathVariable("idPrato") Prato prato, RedirectAttributes attributes) {
		carrinho.add(prato);
		attributes.addFlashAttribute("mensagem", "Prato adicionado com sucesso!");
		return "redirect:/carrinho";
	}

	@GetMapping("/remover/{idPrato}")
	public String rmProduto(@PathVariable("idPrato") Prato prato, RedirectAttributes attributes) {
		carrinho.remove(prato);
		attributes.addFlashAttribute("mensagem", "Prato removido com sucesso!");
		return "redirect:/carrinho";
	}
}
