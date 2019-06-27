package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Prato;
import com.ufc.br.repository.PratoRepository;
import com.ufc.br.util.AulaFileUtils;


@Service
public class PratoService {
	
	@Autowired
	private PratoRepository pratoRepository;
	
	public  Prato salvar(Prato prato) {
		return pratoRepository.save(prato);
	}	
	
	public List<Prato> listarPratos(){
		  return pratoRepository.findAll();
	}
	public void remover(Prato prato) {
		pratoRepository.delete(prato);
	}
	
	public void salvar(Prato prato, MultipartFile imagem) {
		
		String nomeArquivo = imagem.getOriginalFilename();
		String nomeImagem = prato.getNomePrato() + " - " + nomeArquivo;
		

		
		prato.setImagem(nomeImagem);
		
		String caminho = "images/" + prato.getImagem();
		AulaFileUtils.salvarImagem(caminho,imagem);
		
		pratoRepository.save(prato);
	}
	
}


