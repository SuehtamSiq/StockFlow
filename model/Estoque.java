package model;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> lista = new ArrayList<>();
    
    public void adicionarProduto(Produto p) {
        lista.add(p);
    }
    
    public List<Produto> getLista() {
        return lista;
    }

    public Produto buscarPorCodigo(String codigo) {
        return lista.stream()
            .filter(p -> p.getCodigo().equals(codigo))
            .findFirst()
            .orElse(null);
    }
}
