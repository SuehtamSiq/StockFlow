package model;

import java.util.ArrayList;
import java.util.List;

// lasse responsável pelo armazenamento e gerenciamento da lista de produtos.
public class Estoque {
    private List<Produto> lista = new ArrayList<>();
    
    // Adiciona um novo produto na lista de estoque.
    public void adicionarProduto(Produto p) {
        lista.add(p);
    }
    
    public List<Produto> getLista() {
        return lista;
    }

    // Realiza busca de produto através do código informado.
    public Produto buscarPorCodigo(String codigo) {
        return lista.stream()
            .filter(p -> p.getCodigo().equals(codigo))
            .findFirst()
            .orElse(null);
    }
}
