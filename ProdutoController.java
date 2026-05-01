package controller;

import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoController {
  
    private List<Produto> listaProdutos = new ArrayList<>();

    public void cadastrarProduto(String codigo, String nome, int quantidade, double custo) {
        
        Produto p = new Produto(codigo, nome, quantidade, custo);
        
        listaProdutos.add(p);
        
        System.out.println("Salvo! Total na lista: " + listaProdutos.size());
    }
    
    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }
}