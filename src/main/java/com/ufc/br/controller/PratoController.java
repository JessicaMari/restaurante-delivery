package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufc.br.model.Prato;
import com.ufc.br.service.PratoService;


@Controller
@RequestMapping("/prato")
public class PratoController {
	
	@Autowired
	private PratoService pratoService;
	
	@GetMapping("/cadastrar")
	public ModelAndView cadatro(Prato prato) {
		ModelAndView andView = new ModelAndView("pratos/cadastro");
		andView.addObject("prato",prato);
		return andView;
	}
	
	
	@PostMapping("/cadastrar")
	public ModelAndView salvar(@Validated Prato prato, BindingResult result, RedirectAttributes attributes,@RequestParam(value= "imagem") MultipartFile imagem) {


		attributes.addFlashAttribute("mensagem", "Prato cadastrado com sucesso!");
		pratoService.salvar(prato,imagem);
		ModelAndView mv = new ModelAndView("redirect:/prato/listar");

	    return mv;
	}
	

   @GetMapping("/listar")
   public ModelAndView lista() {
   List<Prato> pratos=pratoService.listarPratos();
	   ModelAndView andView = new ModelAndView("pratos/listar");
	   andView.addObject("pratos",pratos);
	   return andView;	
   }
   
   @GetMapping("/deletar/{id}")
   public String remover(@PathVariable("id") Prato prato, RedirectAttributes attributes) {
      
       if(prato != null) {
           pratoService.remover(prato);
       }

       attributes.addFlashAttribute("mensagem", "Prato deletado com sucesso");

       return "redirect:/prato/listar";
   }

}
	
	