package controller;

import java.util.List;
import model.Estoque;
import model.Produto;

//Classe responsável por controlar as regras de negócio dos produtos e materiais.
//Atua como intermediária entre as telas (View) e os dados (Model).
public class ProdutoController {
  
    private Estoque estoque = new Estoque();
    
    // Define o estoque mínimo padrão para gerar alertas no sistema.
    public static final int ESTOQUE_MINIMO = 30;

    // Método responsável por cadastrar novos produtos no estoque.
	// Realiza validação para impedir códigos duplicados.
    public void cadastrarProduto(String codigo, String nome, int quantidade, double custo) throws IllegalArgumentException {
    	
        if (buscarPorCodigo(codigo) != null) {
        	// Validação de duplicidade
            throw new IllegalArgumentException("O código '" + codigo + "' já está cadastrado em outro produto!");
        }
        
        Produto p = new Produto(codigo, nome, quantidade, custo);
        estoque.adicionarProduto(p);
        System.out.println("Salvo! Total no estoque: " + estoque.getLista().size());
    }

    
    public List<Produto> getListaProdutos() {
        return estoque.getLista();
    }

 // Busca um produto no estoque utilizando seu código único.
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
