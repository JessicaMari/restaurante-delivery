package com.ufc.br.carrinho;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.ufc.br.model.Prato;

@SessionScope
@Component
public class Carrinho {
    private List<Prato> pratos = new ArrayList<>();
    
    public void add(Prato prato) {
        pratos.add(prato);
    }

    public List<Prato> list() {
        return pratos;
    }

    public void remove(Prato prato) {
       pratos.remove(prato); 
    }

    public Double calcularValorTotal() {
		Double total = new Double(0);
        for (Prato prato : pratos)
            total += Double.valueOf(prato.getPrecoPrato());
		return total;
    }
    
    public Integer getQuantidade() {
        return pratos.size();
    }

    public void limpar() {
        this.pratos.clear();
    }

}
