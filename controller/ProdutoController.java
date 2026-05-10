package controller;

import java.util.List;
import model.Estoque;
import model.Produto;

public class ProdutoController {
  
    private Estoque estoque = new Estoque();
    
    // Constante centralizada para evitar duplicação (Melhoria 2)
    public static final int ESTOQUE_MINIMO = 30;

    public void cadastrarProduto(String codigo, String nome, int quantidade, double custo) throws IllegalArgumentException {
        // Validação de duplicidade
        if (buscarPorCodigo(codigo) != null) {
            throw new IllegalArgumentException("O código '" + codigo + "' já está cadastrado em outro produto!");
        }
        
        Produto p = new Produto(codigo, nome, quantidade, custo);
        estoque.adicionarProduto(p);
        System.out.println("Salvo! Total no estoque: " + estoque.getLista().size());
    }

    
    public List<Produto> getListaProdutos() {
        return estoque.getLista();
    }

    public Produto buscarPorCodigo(String codigo) {
        return estoque.buscarPorCodigo(codigo);
    }
public void adicionarMaterialAoProduto(String codigoProduto, Produto novoMaterial) {
    Produto pai = buscarPorCodigo(codigoProduto);
    if (pai != null) {
        // Busca se o material já existe na árvore BOM deste produto pai
        Produto materialExistente = pai.getMateriais().stream()
            .filter(mat -> mat.getCodigo().equals(novoMaterial.getCodigo()))
            .findFirst()
            .orElse(null);

        if (materialExistente != null) {
            // Se já existe, acumula a quantidade e atualiza com o novo custo
            int novaQuantidade = materialExistente.getUnidade() + novoMaterial.getUnidade();
            materialExistente.setUnidade(novaQuantidade);
            materialExistente.setCusto(novoMaterial.getCusto()); 
            System.out.println("Material atualizado! Nova Qtd: " + novaQuantidade);
        } else {
            // Se for um material inédito para este pai, adiciona normalmente
            pai.adicionarMaterial(novoMaterial);
            System.out.println("Novo material adicionado à árvore.");
        }
    }
}

}
