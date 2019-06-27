package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Usuario;
import com.ufc.br.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping("/cadastrar")
	public ModelAndView cadatro(Usuario usuario) {
		ModelAndView andView = new ModelAndView("usuario/cadastro");
		andView.addObject("usuario", usuario);
		return andView;
	}

	@PostMapping("/cadastrar")
	public ModelAndView salvar(@Validated Usuario usuario, BindingResult result) {

		ModelAndView mv = new ModelAndView("usuario/cadastro");

		mv.addObject("mensagem", "Pessoa cadastrada com sucesso!");
		usuarioService.salvar(usuario);

		return mv;
	}

	@RequestMapping("/logar")
	public ModelAndView logar() {
		ModelAndView mv = new ModelAndView("usuario/login");
		return mv;
	}

}
